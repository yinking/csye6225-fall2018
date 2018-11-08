. ./config.sh
if [ -n "$1" ]
then
	aws s3 rm s3://$awsBucketName --recursive
	aws cloudformation delete-stack --stack-name $1
else
	echo "input stack name!"
fi
