if [ -n "$1" ]
then
	aws cloudformation delete-stack --stack-name $1
else
	echo "input stack name!"
fi