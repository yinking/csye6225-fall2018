if [ -n "$1" ]
then
	aws cloudformation create-stack --stack-name $1 --template-body file://csye6225-cf-WAF.json
else
	echo "input stack name!"
fi