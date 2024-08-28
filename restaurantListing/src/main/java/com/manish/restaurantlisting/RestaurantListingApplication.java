package com.manish.restaurantlisting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantListingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantListingApplication.class, args);
	}

}



/*

First of all, I want to configure the port for my application.
Now I want all my microservices restaurant listing microservice, food catalog, microservice and order
service to work on 909X series. So I'm going to use 909X series for all the server port configuration.
So my server dot port will be 9091 for restaurant listing service 9092 for food catalog and 9093 for
order source.
___________
server:
  port: 9091
______________________________________________________________


______________________________________________________________

I also want this application to register to Eureka to make it as a Eureka client,
I need to tell where to look for Eureka Server.
So I'm going to add now the properties to connect to the Eureka Server.
So Eureka Client service URL to localhost 8761.

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/

Now before starting this application, also we need to remember to start our Eureka server, otherwise
you will get the connection error.
______________________________________________________________


______________________________________________________________

application:
    name: RESTAURANT-SERVICE

We also have given the name to our application.
This name will be responsible to be registered to Eureka Server.
So when I run this application with Eureka Server on, I should be able to see this application registered
in the Eureka dashboard.



I now need to connect to my data source that is MySQL database.
So for that we have few properties to make a connection to the data source.
So spring -> datasource -> URL: jdbc:mysql://localhost:3306/restaurantdb.

I'm going to put my user ID password, my MySQL driver, which is going to be the driver for MySQL connection.


Also, I want that spring JPA Hibernate Properties to be set.
I want that  [hibernate: ddl-auto: update]. ->
This means that if I have it as update every time, my data will not be discarded and new tables will
not be created.
if I have made it as not update rather than create i.e -> [ddl-auto: create]
then every time I start my application, all the data in the database tables will be removed,
new tables will be created which I do not want. I want to have my data persisted in my database.

Second is [show-sql: true] why I have done this True because I want to show what all queries I'm going
to hit on the console.

database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
MySQL5InnoDBDialect is going to be the one who is responsible to convert your ORM based language to my SQL based query.
In my service layer with repository, I said [restaurantRepo.findAll()] this is ORM language i.e English language.

How it will convert into select Star from restaurantDB from ORM Language? How will it do that?

SO using this hibernate dialect, which is a interface that converts your ORM language to database specific language.
This is important whenever you have a framework at one side and a data source like MySQL or Oracle on
another side, if you have used Oracle, this dialect would have changed because the querying pattern
for MySQL is different from the Oracle.

Now everything is configured.


spring:
  application:
    name: RESTAURANT-SERVICE
  datasource:
    url: jdbc:mysql://localhost:3306/restaurantdb
    password: root
    username: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect

*/