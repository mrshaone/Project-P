# Project-P
This project is a simple file manager built with Spring boot, containing upload and download functionalities. This project is only for educational purposes and developers doesn't have any intent for commercial uses!
# Local run
Requirements:
+ JDK 19
+ Apache Maven
### Windows
1. Create a directory on root of the project and name it `uploads`.
2. Each services contains `application.properties`, make sure active profile is set to dev.
3. Run `mvn clean` and then `mvn install` in core, filemanager and notification directory.
4. Start each service you want to use.
# Docker
1. Each services contains `application.properties`, make sure active profile is set to prod.
2. Simply run `docker-compose up -d --build` on project base directory.
3. Have fun! 
<br/><br/>
## Default ports:
Core: [1230](http://localhost:1230)<br/>
Filemanager: [1231](http://localhost:1231)<br/>
Notification: [1232](http://localhost:1232)<br/>
MongoDB: [27017](http://localhost:27017)<br/>

# Services Description
## core
This service is the main service, and it is responsible for fetching requests and process them. It's also responsible for storing file details in MongoDB.
<br/><br/>
#### API Maps:
+ Upload (POST): [/file](http://localhost:1230/file)
> Fetches a multipart file from the request and stores the details in MongoDB  and passes the file to filemanager service.
+ Download (GET): [/file?uuid={uuid}](http://localhost:1230/file)
> Fetches a String UUID and if the file is present it'll return the file in response from filemanager service.
<br/>

## filemanager
This service is responsible for saving two copy of files given to it, one on local file system and one on MongoDB. After successfully saving files it'll generate a UUID. That UUID will be the key for downloading file. 
<br/><b>Important: Don't send data directly to filemanager service and only use core service!</b>
<br/><br/>
#### API Maps:
+ Upload (POST): [/file](http://localhost:1231/file)
  > Gets the multipart file from core service and store it on MongoDB and local file storage and generates a UUID and sends it as response.
+ Download (GET): [/file](http://localhost:1231/file)
  > Gets UUID as parameter and if it's a valid UUID it'll return the corresponding file.  

## notification
This service is only an implementation for RabbitMQ and Spring Boot integration. Simply when user pass a file to core service, it'll send UUID to RabbitMQ and notification service consume the queue and displays the UUID.