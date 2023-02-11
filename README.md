# Quarkus integration into Wicket

## Introduction

The goal of this project is to show how to integrate Wicket with Quarkus.

Before presenting the integration solution, a quick word about Quarkus competitor,
**Spring Boot**. This will make you understand better the context we're in
when we try to create a Quarkus (Web) application. 

Spring Boot allows you to create stand-alone applications
(by declaring `JAR` packaging in `pom.xml`) or WEB applications 
(by declaring `WAR` packaging). Stand-alone application may run with 
`java -jar XXX.jar` while web applications may run in the following ways:
- Deploy the `*.war` file into a Java Servlets supporting web server (like Tomcat), OR
- Run it same way as a stand-alone application, like this: `java -jar XXX.war`.
In this second case the embedded web server that you configured (default 
is embedded Tomcat) with Spring Boot is started and the web application
is installed and served through that (embedded) web server.

In case of Quarkus, only `JAR` packaging is possible and web applications
may run only the same way as stand-alone applications. Also, embedded 
web server is different.

## How to Start?
You must create a Quarkus application with **Undertow** extension. 
This extension offers Java Servlets support, therefore allows integrating
Wicket inside. The `pom.xml` from the 
[following](./quarkus-integration-example) directory contains such 
a definition (with main properties defined in parent `pom.xml`). 
Here are the added dependencies:
```XML
    <dependencies>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-arc</artifactId>
        </dependency>
        <dependency>
            <groupId>io.quarkus</groupId>
            <artifactId>quarkus-undertow</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.wicket</groupId>
            <artifactId>wicket-core</artifactId>
        </dependency>
    </dependencies>
```
By adding these dependencies Wicket and Quarkus will run together (meaning
inside the same JVM) BUT from Wicket pages and resources you cannot 
use Quarkus beans. There is no `"Bean Injector"` defined in Wicket
that will inject the beans. Also, please notice there is a `web.xml`
file present in the project, and it's not in the usual place (under `webapps`)
because Undertow requires it to be elsewhere. The 
[quarkus-integration-example](./quarkus-integration-example) module
from this project contains a fully working solution, including the web.xml 
and the wicket application configuration inside.


## Adding the Quarkus Injector (based on Google Guice)
There are many injection solutions defined for Wicket, but they do not work 
with Quarkus. The best candidate is 
[JavaEEComponentInjector](https://mvnrepository.com/artifact/org.wicketstuff/wicketstuff-javaee-inject)
but it calls a method that is not implemented by Quarkus. In order 
to make this work, the easiest way is to implement your own injector. 

I found that **Guice Injector** is very
simple and easy to understand but you need to have a collection of
`com.google.inject.Module`s or an `com.google.inject.Injector` instance.
Having Modules is not useful nor possible. Implementing an `Injector` 
based on Quarkus is possible. The following dependency needs to be added
to your Wicket project.
```XML
    <dependency>
        <groupId>ro.nemeti.wicket</groupId>
        <artifactId>wicket-quarkus-integration-through-guice</artifactId>
        <version>9.12.0</version>
    </dependency>
```
Also, the Wicket application needs to have the following in init:
```Java
    @Override
    protected void init() {
        super.init();
        
        // do the injection part
        Injector injector = new CdiBasedInjectorImpl(CDI.current());
        getComponentInstantiationListeners().add(new GuiceComponentInjector(this, injector));
        
        // other configuration settings here
    }
```
Once you have this your injector should be fine. 
If problems might exist with unloaded beans, add the following in 
`application.properties`
```
quarkus.arc.remove-unused-beans=false
```

## Creating own Injector
Since Google Guice is not so difficult, I'm working on my own injector
that works directly with Quarkus.
To Be Continued...