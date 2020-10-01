# spring-boot-api

## Application.yml content
#### src/main/resources/application.yml

server:
  port : 8080
jwt:
  secret: <<secret to produce jwt>>
spring:
  datasource:
    url: <<url of database>>
    password: <<databse password>>
    username: <<database username>>
    driver-class-name: "com.mysql.cj.jdbc.Driver"
  jpa:
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    hibernate:
      ddl-auto: none
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
        implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl


## Database structure
languages: id(pk): INT, languageCode: VARCHAR(8)
words: id(pk): INT, word: VARCHAR(255), language(fk=> languages:id)
users: username(pk): VARCHAR(250), passwordEncrypted: VARCHAR(250)