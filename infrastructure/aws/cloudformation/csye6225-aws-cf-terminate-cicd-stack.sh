. ./config.sh
if [ -n "$1" ]
then
	aws cloudformation delete-stack --stack-name $1
	aws s3 rm s3://$codeDeployS3Bucket --recursive
else
	echo "input stack name!"
fi
