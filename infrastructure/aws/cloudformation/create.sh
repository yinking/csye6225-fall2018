if [ -n "$1" ]
then
	aws cloudformation create-stack --stack-name $1 --template-body file://networking.json --parameters ParameterKey=Name,ParameterValue=$1"-csye6225-"
else
	echo "input stack name!"
fi
