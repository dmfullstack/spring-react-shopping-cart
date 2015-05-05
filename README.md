# spring-react-shopping-carty
A shopping cart example written in Spring and React using Nashorn for templating.


##JDK 8 Notes
Nashorn is still in active development, it is recommended that you use the latest version possible.
The latest releases are available at https://jdk8.java.net/download.html

## Eclipse
Run the following in a terminal.

```
gradle eclipse
```

Open Eclipse and click
File -> Import -> Existing Projects into Workspace

## How to run it ?

The application does not work yet when packaged as a Spring Boot fat JAR or WAR.
You should run Run IsomorphicApplication main class in your IDE.

Be sure to use the latest [JDK 8 build](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
in order to have the better Nashorn version available.
