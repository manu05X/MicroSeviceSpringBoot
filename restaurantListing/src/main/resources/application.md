server:
  port: 9091


> Below is the edreka so it is local environment so we will keep in local
[//]: # (eureka:)

[//]: # (  client:)

[//]: # (    service-url:)

[//]: # (      defaultZone: http://localhost:8761/eureka/)

spring:
  application:
    name: RESTAURANT-SERVICE
> These will change according to the environment and below is local to the environment so move to local
[//]: # (  datasource:)

[//]: # (    url: jdbc:mysql://localhost:3306/restaurantdb)

[//]: # (    password: 2122)

[//]: # (    username: root)
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
  

> In local we will have the local url and dev we will have dev url