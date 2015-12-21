# SpringBootSecurity
Web Project using spring boot. Tried to use functionality that we can do using spring boot.

* Spring Security.
* Spring Tiles.
* Custom JSP  which are independent of thyme leaf.
* Method level granularity in security.
* Mapping Jackson.

##Using the application for debug purpose.

* Download the package.
*  It runs on  java 1.8 can change the version to 1.7 if needed .
*  to Run 
 

>     mvn package && java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar target/employeeSecurity-1.0.jar


