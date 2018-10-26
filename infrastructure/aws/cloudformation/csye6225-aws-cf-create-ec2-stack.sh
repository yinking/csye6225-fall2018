. ./config.sh
if [ -n "$1" ]
then
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-ec2.json --parameters ParameterKey=Name,ParameterValue=$1"-csye6225-" ParameterKey=awsBucketName,ParameterValue=$awsBucketName ParameterKey=localLocation,ParameterValue=$localLocation ParameterKey=awsAccessKeyId,ParameterValue=$awsAccessKeyId ParameterKey=awsSecretKey,ParameterValue=$awsSecretKey ParameterKey=accountNumber,ParameterValue=$accountNumber
else
	echo "input stack name!"
fi