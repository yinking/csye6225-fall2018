if [ -n "$1" ]
then
	if [ -r $1 ]
	then
		echo "exist stack name!"
	else
		stack='{"vpcs":[],"subnets":[]}'
		vpc=$(aws ec2 create-vpc --cidr-block 10.0.0.0/16)
		stack=$(echo $stack | jq ".vpcs += [$vpc]")
		vpcId=$(echo $vpc | jq -r ".Vpc.VpcId")
		subnet=$(aws ec2 create-subnet --vpc-id $vpcId --cidr-block 10.0.1.0/24)
		stack=$(echo $stack | jq ".subnets += [$subnet]")
		echo $stack > ./stack1
	fi
else
	echo "input stack name!"
fi
