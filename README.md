# spring-react-shopping-carty
A shopping cart example written in Spring and React using Nashorn for templating.

##Demo

A demo is available at https://salty-anchorage-7333.herokuapp.com/

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

You should run Run IsomorphicApplication main class in your IDE or run 
```
gradle build
```

and then 
```
java -Dserver.port=80 -jar build/libs/spring-react-shopping-cart.jar
```