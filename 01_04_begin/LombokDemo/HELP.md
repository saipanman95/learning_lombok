# Getting Started
To Run this application:
1. Download artifact from CI/CD Pipeline
2. Extract from zip
3. Drill down to directory that contains BannerApplicationMonitor-0.0.1-SNAPSHOT.jar
4. type: java -jar -Dspring.profiles.active=dev BannerApplicationMonitor-0.0.1-SNAPSHOT.jar
5. Open browser to localhost:9000

# Current Features
The Banner Application Monitor currently works only in DEVL environment.  I have not updated the internal H2 Hikari database to include the CLNT and PROD links for the application api health checks or for the PROBE log query calls.

The app when loads presents a dashboard of available badges with basic http status, the hostname of the server it resides and tomcat instance on that server.

## Future Releases
I am hoping to include authentication into the Banner Application Monitor, finishing tying it into Zabbix for turning off monitoring when maintenance is desired and links to the individual applications for each of opening.  The Ethos apps still need to be included as well as the health status of the Rabbit MQ server.
### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.3/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.3/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

