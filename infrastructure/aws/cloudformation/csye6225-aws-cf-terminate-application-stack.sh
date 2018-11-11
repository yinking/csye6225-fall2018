. ./config.sh
if [ -n "$1" ]
then
    domainName=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)
    str='csye6225.com'
    awsBucketName="$domainName$str"
	aws s3 rm s3://$awsBucketName --recursive
	aws cloudformation delete-stack --stack-name $1
else
	echo "input stack name!"
fi
