# jmc-hello-world
Hello World app with Amazon SQS, RDS, EBS, Worker/Webtier, Swagger, etc.

## Elastic Beanstalk Configuration:

### Software Configuration:
Set these environment variables:
   * SERVER_PORT 5000
   * SPRING_PROFILES_ACTIVE prod

### DB Access:
  Only necessary for "full stack" in the hello-world-rest app.
  Webtier connects through URL, as long as it is in the security group for RDS.

    jdbc:mysql://aaxfgfb2zfv3xb.cfkkwmdp41io.us-west-2.rds.amazonaws.com:3306/hello_world
Master login: devuser/letmeinnow

  * Make changes to DB instance via RDS config page: Instances > Instance Actions > Modify (Had to set 'Publicly Available' = Yes here to make it visible over Internet)
  * Add my IP to the db security group
  * Set the password (again) in the EBS DB configuration section 
 
### SSH Access to Instances:
  * Enable the keypair in the configuration
  * Add IP to security group for the instance


    jcrump@JCRUMPMAC3:~/jmc/aws$ ssh -i /Users/jcrump/jmc/aws/jcrump-adminuser.pem ec2-user@ec2-54-202-64-115.us-west2.compute.amazonaws.com

### Initialize Database

Once you can use 'mysql' to log in to the DB instance, set up the schema:

% mysql -h aaxfgfb2zfv3xb.cfkkwmdp41io.us-west-2.rds.amazonaws.com -P 3306 --user=devuser --password=letmeinnow

    create database hello_world;
    use hello_world;create table greetings (id INT unique not null, value VARCHAR(1024));
    insert into greetings (id, value) values (1, "Hello Amazon");
    select * from greetings;
 
App Notes:
==========

* Use my 'adminuser' AWS account

#### Swagger
* Local Swagger: http://localhost:8080/swagger-ui.html
* AWS Swagger: http://jmc-helloworld-env.us-west-2.elasticbeanstalk.com/swagger-ui.html
   * No port, it's mapped automatically
   
#### HTTP:
  curl -X GET -i 'http://jmc-helloworld-env.us-west-2.elasticbeanstalk.com/greeting?id=2'

Payload message:

{
  "id": 99,
  "value": "Hello World 99!"
}
