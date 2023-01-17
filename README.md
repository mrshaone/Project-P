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
2. Run `mvn clean` and then `mvn install` in core, filemanager and notification directory.
3. On base directory run `docker-compose up -d --build
<br/><br/>
## Default ports:
Core: [1230](http://localhost:1230)<br/>
Filemanager: [1231](http://localhost:1231)<br/>
Notification: [1232](http://localhost:1232)<br/>
MongoDB: [27017](http://localhost:27017)<br/>
