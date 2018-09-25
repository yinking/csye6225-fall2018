if [ -n "$1" ]
then
	if [ -r $1 ]
	then
		for subnetId in $(jq -r '.subnets[].Subnet.SubnetId' $1)
		do
			aws ec2 delete-subnet --subnet-id $subnetId
		done
   		for vpcId in $(jq -r '.vpcs[].Vpc.VpcId' $1)
		do
			aws ec2 delete-vpc --vpc-id $vpcId
		done
		rm -f $1
	else
		echo "no stack name!"
	fi
else
	echo "input stack name!"
fi
