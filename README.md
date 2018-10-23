# csye6225-fall2018-repo-template
Han Luo       :luo.han1@husky.neu.edu
Zhiyong Zhang :zhang.zhiyo@husky.neu.edu
Ying Wang     :wang.ying6@husky.neu.edu
Yu Fang       :fang.yu1@husky.neu.edu

## Assignment2

# To run Web Application

Prerequisite:
	
	1) Have postgresql-10 installed
	2) Find file pg_hba.conf in directory: admin:///var/lib/pgsql/10/data
	  make changes to the last 11 lines: change all the content "METHOD" catagory into "trust"

1. Create database

	Entering psql command line
	1) run: systemctl restart postgresql-10 in terminal
	2) run: sudo su - postgres
	3) run: psql

	Checking current database
	1) run: \l
	2) run: create database db1; (name should be consistent with db1)
	3) run: \l (check if created successfully)
	4) run: \q (quit psql command)

	run: exit (logout)

2. Run Web Application (backend)

	1) Open file "webapp" with IDE(Intellij)
	2) Run project

3. Run web Application (Frontend)
	
	1) Open terminal go to directory file "vueapp"
	2) run: npm i (install node module)
	3) run: npm run serve
	4) To register open url: localhost:8081/#/student/register
	5) Go to any browser open url link to login: localhost:8081
	6) To check current time and all the registered accounts go to url:localhost:8081/#/student/time

5. Run Unit test
	In file "webapp" run test java file "DemoApplicationTests"

6. Travis CI build link:  https://travis-ci.com/Rohannson/csye6225-fall2018

## Assignment3. Create database

1. Create Database
	Entering psql command line
	1) run: systemctl restart postgresql-10
	2) run: sudo su - postgres
	3) run: psql

	Checking current database
	1) run: \l
	2) run: create database db2; (name should be consistent with db1)
	3) run: \l (check if created successfully)
	4) run: \q (quit psql command)

2. Run web application (back end)

    1) Open file "webapp" with IDE(Intellij)
	2) Run project in main, runnable class

3. Open postman application

    1) Click the button on the right of new+ button
    2) Import file in directory: /postman/Assignment3.postman_collection.json
    3) After import, Under user folder "POST" will create new users, and "GET" will return existing users. "Body" part of the post can input username and    password
    4) Under Transaction folder, "GET" would be part will return existing transactions, "POST" would new transaction, "DEL" can delete a transaction, and "PUT" can change the content of an existing transaction
    5) To determin which transaction to operate, change the URL content to the transaction ID to be ultered or deleted and the ID would be added to the end of transaction.

as





