. ./config.sh
if [ -n "$1" ]
then
    	domainName=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)
    	domainName=${domainName%?}
	aws s3 rm s3://$domainName".csye6225.com" --recursive
	aws cloudformation delete-stack --stack-name $1
else
	echo "input stack name!"
fi
