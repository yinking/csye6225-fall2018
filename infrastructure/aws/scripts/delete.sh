for internetGateway in $(aws ec2 describe-internet-gateways | jq -c '.InternetGateways[]')
do
	internetGatewayId=$(echo $internetGateway | jq -r '.InternetGatewayId')
	for vpcId in $(echo $internetGateway | jq -r '.Attachments[].VpcId')
	do
		aws ec2 detach-internet-gateway --internet-gateway-id $internetGatewayId --vpc-id $vpcId
		aws ec2 delete-internet-gateway --internet-gateway-id $internetGatewayId
	done
done
for subnetId in $(aws ec2 describe-subnets | jq -r '.Subnets[].SubnetId')
do
	aws ec2 delete-subnet --subnet-id $subnetId
done
for vpcId in $(aws ec2 describe-vpcs | jq -r '.Vpcs[].VpcId')
do
	aws ec2 delete-vpc --vpc-id $vpcId
done
