. ./config.sh
if [ -n "$1" ]
then
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-application.json --parameters ParameterKey=Name,ParameterValue=$1"-csye6225-" ParameterKey=awsBucketName,ParameterValue=$awsBucketName
else
	echo "input stack name!"
fi