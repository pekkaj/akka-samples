# Akka/REST Project Template

This project is a template for getting started with SBT and Akka''s REST support.  It simply provides an initial development environment for experimentation.

## Notes
* Because this project uses the AkkaServlet to run the application in a separate container, you do not need to download or clone the akka project.  
* Use the src/main/resource/akka.conf to configure any akka settings
* If you already have SBT configured and akka installed on your system make sure to use or at least look at the included scripts for running SBT

## Getting started
1. cd akka-template-rest
2. ./sbt (unix-based) or sbt (windows)
3. update
4. jetty-run
5. http://localhost:8080/hello

## Changelog

2010-04-06

* upgraded to 0.8.1 akka
* added a windows sbt script
