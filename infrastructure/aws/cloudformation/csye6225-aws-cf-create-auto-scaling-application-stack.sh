. ./config.sh
if [ -n "$1" ]
then
	domainName=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)
    	domainName=${domainName%?}
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-auto-scaling-application.json --parameters ParameterKey=stackName,ParameterValue=$1 ParameterKey=domainName,ParameterValue=$domainName ParameterKey=awsAccessKeyId,ParameterValue=$awsAccessKeyId ParameterKey=awsSecretKey,ParameterValue=$awsSecretKey ParameterKey=accountNumber,ParameterValue=$accountNumber ParameterKey=certificatesArn,ParameterValue=$certificatesArn
else
	echo "input stack name!"
fi
