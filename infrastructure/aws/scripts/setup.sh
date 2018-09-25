if [ -n "$1" ]
then
	length=$(aws ec2 describe-tags --filters "Name=key,Values=Name" "Name=value,Values=$1" | jq ".Tags|length")
	if [ $length == 0 ]
	then
		vpcId=$(aws ec2 create-vpc --cidr-block 10.0.0.0/16 | jq -r ".Vpc.VpcId")

		subnetId=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block 10.0.1.0/24 | jq -r ".Subnet.SubnetId")

		internetGatewayId=$(aws ec2 create-internet-gateway | jq -r ".InternetGateway.InternetGatewayId")

		aws ec2 attach-internet-gateway --internet-gateway-id $internetGatewayId --vpc-id $vpcId

		routeTableId=$(aws ec2 create-route-table --vpc-id $vpcId | jq -r ".RouteTable.RouteTableId")

		associationId=$(aws ec2 associate-route-table --route-table-id $routeTableId --subnet-id $subnetId | jq -r ".AssociationId")

		return=$(aws ec2 create-route --route-table-id $routeTableId --destination-cidr-block 0.0.0.0/0 --gateway-id $internetGatewayId | jq -r ".Return")
		
		aws ec2 create-tags --resources $vpcId $subnetId $internetGatewayId $routeTableId --tags Key=Name,Value=$1
		
	else
		echo "exist stack name!"
	fi
else
	echo "input stack name!"
fi
