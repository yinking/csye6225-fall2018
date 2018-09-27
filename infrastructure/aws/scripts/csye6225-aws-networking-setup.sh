. ./config.sh
if [ -n "$1" ]
then
	length=$(aws ec2 describe-tags --filters "Name=key,Values=Name" "Name=value,Values=$1" | jq ".Tags|length")
	if [ $length == 0 ]
	then
		vpcId=$(aws ec2 create-vpc --cidr-block $vpccidr | jq -r ".Vpc.VpcId")

		subnet1Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block ${subnet_cidr[0]} --availability-zone ${subnet_zone[0]} | jq -r ".Subnet.SubnetId")
		subnet2Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block ${subnet_cidr[1]} --availability-zone ${subnet_zone[1]} | jq -r ".Subnet.SubnetId")
		subnet3Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block ${subnet_cidr[2]} --availability-zone ${subnet_zone[2]} | jq -r ".Subnet.SubnetId")
		subnet4Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block ${subnet_cidr[3]} --availability-zone ${subnet_zone[0]} | jq -r ".Subnet.SubnetId")
		subnet5Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block ${subnet_cidr[4]} --availability-zone ${subnet_zone[1]} | jq -r ".Subnet.SubnetId")
		subnet6Id=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block ${subnet_cidr[5]} --availability-zone ${subnet_zone[2]} | jq -r ".Subnet.SubnetId")

		internetGatewayId=$(aws ec2 create-internet-gateway | jq -r ".InternetGateway.InternetGatewayId")

		aws ec2 attach-internet-gateway --internet-gateway-id $internetGatewayId --vpc-id $vpcId

		routeTable1Id=$(aws ec2 create-route-table --vpc-id $vpcId | jq -r ".RouteTable.RouteTableId")
		routeTable2Id=$(aws ec2 create-route-table --vpc-id $vpcId | jq -r ".RouteTable.RouteTableId")

		association1Id=$(aws ec2 associate-route-table --route-table-id $routeTable1Id --subnet-id $subnet1Id | jq -r ".AssociationId")
		association2Id=$(aws ec2 associate-route-table --route-table-id $routeTable1Id --subnet-id $subnet2Id | jq -r ".AssociationId")
		association3Id=$(aws ec2 associate-route-table --route-table-id $routeTable1Id --subnet-id $subnet3Id | jq -r ".AssociationId")
		association4Id=$(aws ec2 associate-route-table --route-table-id $routeTable2Id --subnet-id $subnet4Id | jq -r ".AssociationId")
		association5Id=$(aws ec2 associate-route-table --route-table-id $routeTable2Id --subnet-id $subnet5Id | jq -r ".AssociationId")
		association6Id=$(aws ec2 associate-route-table --route-table-id $routeTable2Id --subnet-id $subnet6Id | jq -r ".AssociationId")

		return=$(aws ec2 create-route --route-table-id $routeTable1Id --destination-cidr-block 0.0.0.0/0 --gateway-id $internetGatewayId | jq -r ".Return")
		
		aws ec2 create-tags --resources $vpcId $subnet1Id $subnet2Id $subnet3Id $subnet4Id $subnet5Id $subnet6Id $internetGatewayId $routeTable1Id $routeTable2Id --tags Key=Name,Value=$1
		
	else
		echo "exist stack name!"
	fi
else
	echo "input stack name!"
fi
