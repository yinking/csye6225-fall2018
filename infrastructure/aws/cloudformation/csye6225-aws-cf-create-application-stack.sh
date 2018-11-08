. ./config.sh
if [ -n "$1" ]
then
    NAME=$(aws route53 list-hosted-zones --query "HostedZones[0].Name" --output text)
    NAME=${NAME%?}
    echo $NAME
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-application.json --parameters ParameterKey=stackName,ParameterValue=$1 ParameterKey=awsBucketName,ParameterValue=$awsBucketName ParameterKey=ParamDomainName,ParameterValue=$NAME
else
	echo "input stack name!"
fi
