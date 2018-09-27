if [ -n "$1" ]
then
	filter="Name=tag:Name,Values=$1"

	for routeTable in $(aws ec2 describe-route-tables --filters $filter | jq -c ".RouteTables[]")
	do
		routeTableId=$(echo $routeTable | jq -r '.RouteTableId')
		for associationId in $(echo $routeTable | jq -r '.Associations[].RouteTableAssociationId')
		do
			aws ec2 disassociate-route-table --association-id $associationId
		done
		for route in $(echo $routeTable | jq -c '.Routes[]')
		do
			gatewayId=$(echo $route | jq -r '.GatewayId')
			if [ $gatewayId != "local" ]
			then
				destinationCidrBlock=$(echo $route | jq -r '.DestinationCidrBlock')
				aws ec2 delete-route --route-table-id $routeTableId --destination-cidr-block $destinationCidrBlock
			fi
		done
		aws ec2 delete-route-table --route-table-id $routeTableId
	done
	
	for internetGateway in $(aws ec2 describe-internet-gateways --filters $filter | jq -c ".InternetGateways[]")
	do
		internetGatewayId=$(echo $internetGateway | jq -r '.InternetGatewayId')
		for vpcId in $(echo $internetGateway | jq -r '.Attachments[].VpcId')
		do
			aws ec2 detach-internet-gateway --internet-gateway-id $internetGatewayId --vpc-id $vpcId
		done
		aws ec2 delete-internet-gateway --internet-gateway-id $internetGatewayId
	done
	
	for subnetId in $(aws ec2 describe-subnets --filters $filter | jq -r '.Subnets[].SubnetId')
	do
		aws ec2 delete-subnet --subnet-id $subnetId
	done
	
	for vpcId in $(aws ec2 describe-vpcs --filters $filter | jq -r '.Vpcs[].VpcId')
	do
		aws ec2 delete-vpc --vpc-id $vpcId
	done
	
else
	echo "input stack name!"
fi
