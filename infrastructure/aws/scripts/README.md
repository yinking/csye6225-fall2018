Follow these steps to run the shell script:

1. Give file "csye6225-aws-networking-setup.sh" and file "csye6225-aws-networking-teardown.sh" the authority to run by running the following commands:
	
	chmod +x File_Name

2. File "csye6225-aws-networking-setup.sh" has the function of seting up a VPC according to the requirment, file "csye6225-aws-networking-teardown.sh" will    teardown the network resources attached to that VPC

3. Run file "csye6225-aws-networking-setup.sh" and add the name user wish to give this VPC in the back

	./csye6225-aws-networking-setup.sh VPC_NAME

4. To teardown any VPC, run file "csye6225-aws-networking-teardown.sh" followed by the name of the VPC user wish to teardown

	./csye6225-aws-networking-teardown.sh VPC_NAME

5. If user wish to change configurations of the VPC to be created, user should be configuring "config.sh". This file have the configurations of Cidr block of VPC, Cider Block of subnets, and Avaliability zone of subnets
