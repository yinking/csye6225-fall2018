1. Run The following command for the two shell scripts

	chmod +x FILE_NAME

1. Run file "csye6225-aws-cf-create-stack.sh" and followed by the VPC name intended to be created will create the corresponding stack and VPC

	./csye6225-aws-cf-create-stack.sh STACK_NAME

2. Run file "csye6225-aws-cf-terminate-stack.sh" followed by the stack name intended to be deleted would delete the stack and resources attached to it

	./csye6225-aws-cf-terminate-stack.sh STACK_NAME

3. In file "csye6225-cf-networking.json" stores the configuration for the created stack
