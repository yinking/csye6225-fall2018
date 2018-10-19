if [ -n "$1" ]
then
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-cicd.json --parameters ParameterKey=Name,ParameterValue=$1"-csye6225-" --capabilities CAPABILITY_NAMED_IAM
else
	echo "input stack name!"
fi
