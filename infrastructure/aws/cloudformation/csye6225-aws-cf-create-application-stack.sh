. ./config.sh
if [ -n "$1" ]
then
<<<<<<< HEAD
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-application.json --parameters ParameterKey=Name,ParameterValue=$1"-csye6225-" ParameterKey=accountNumber,ParameterValue=$accountNumber ParameterKey=awsBucketName,ParameterValue=$awsBucketName
=======
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-application.json --parameters ParameterKey=Name,ParameterValue=$1 ParameterKey=accountNumber,ParameterValue=$accountNumber ParameterKey=localLocation,ParameterValue=$localLocation ParameterKey=awsAccessKeyId,ParameterValue=$awsAccessKeyId ParameterKey=awsSecretKey,ParameterValue=$awsSecretKey ParameterKey=awsBucketName,ParameterValue=$awsBucketName
>>>>>>> 139516ece1673c2cc16787a08a4f4e3f51c5b4a9
else
	echo "input stack name!"
fi