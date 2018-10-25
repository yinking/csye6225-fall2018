. ./config.sh
if [ -n "$1" ]
then
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-application.json --parameters ParameterKey=Name,ParameterValue=$1"-csye6225-" ParameterKey=domain,ParameterValue=$domain ParameterKey=accountNumber,ParameterValue=$accountNumber,ParameterKey=localLocation ParameterValue=$localLocation,ParameterKey=awsAccessKeyId ParameterValue=$awsAccessKeyId,ParameterKey=awsSeceretKey ParameterValue=$awsSeceretKey,ParameterKey=awsBucketName ParameterValue=$awsBucketName
else
	echo "input stack name!"
fi
