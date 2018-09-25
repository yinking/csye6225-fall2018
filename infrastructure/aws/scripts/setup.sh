if [ -n "$1" ]
then
	length=$(aws ec2 describe-tags --filters "Name=key,Values=Name" "Name=value,Values=$1" | jq ".Tags|length")
	if [ $length == 0 ]
	then
		vpcId=$(aws ec2 create-vpc --cidr-block 10.0.0.0/16 | jq -r ".Vpc.VpcId")

		subnet1Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block 10.0.1.0/24 --availability-zone us-east-1a | jq -r ".Subnet.SubnetId")
		subnet2Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block 10.0.2.0/24 --availability-zone us-east-1b | jq -r ".Subnet.SubnetId")
		subnet3Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block 10.0.3.0/24 --availability-zone us-east-1c | jq -r ".Subnet.SubnetId")

		internetGatewayId=$(aws ec2 create-internet-gateway | jq -r ".InternetGateway.InternetGatewayId")

		aws ec2 attach-internet-gateway --internet-gateway-id $internetGatewayId --vpc-id $vpcId

		routeTableId=$(aws ec2 create-route-table --vpc-id $vpcId | jq -r ".RouteTable.RouteTableId")

		association1Id=$(aws ec2 associate-route-table --route-table-id $routeTableId --subnet-id $subnet1Id | jq -r ".AssociationId")
		association2Id=$(aws ec2 associate-route-table --route-table-id $routeTableId --subnet-id $subnet2Id | jq -r ".AssociationId")
		association3Id=$(aws ec2 associate-route-table --route-table-id $routeTableId --subnet-id $subnet3Id | jq -r ".AssociationId")

		return=$(aws ec2 create-route --route-table-id $routeTableId --destination-cidr-block 0.0.0.0/0 --gateway-id $internetGatewayId | jq -r ".Return")
		
		aws ec2 create-tags --resources $vpcId $subnet1Id $subnet2Id $subnet3Id $internetGatewayId $routeTableId --tags Key=Name,Value=$1
		
	else
		echo "exist stack name!"
	fi
else
	echo "input stack name!"
fi
