. ./config.sh
if [ -n "$1" ]
then
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-cicd.json --parameters ParameterKey=Name,ParameterValue=$1"-csye6225-" ParameterKey=codeDeployS3Bucket,ParameterValue=$codeDeployS3Bucket ParameterKey=accountNumber,ParameterValue=$accountNumber --capabilities CAPABILITY_NAMED_IAM
else
	echo "input stack name!"
fi
