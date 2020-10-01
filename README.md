# spring-boot-api

## Application.yml content
#### src/main/resources/application.yml

server:</br>
&nbsp;port : 8080</br>
jwt:</br>
&nbsp;secret: <<secret to produce jwt>></br>
spring:</br>
&nbsp;datasource:</br>
&nbsp;&nbsp;url: <<url of database>></br>
&nbsp;&nbsp;password: <<databse password>></br>
&nbsp;&nbsp;username: <<database username>></br>
&nbsp;&nbsp;driver-class-name: "com.mysql.cj.jdbc.Driver"</br>
&nbsp;jpa:</br>
&nbsp;&nbsp;database-platform: org.hibernate.dialect.MySQL5InnoDBDialect</br>
&nbsp;&nbsp;hibernate:</br>
&nbsp;&nbsp;&nbsp;ddl-auto: none</br>
&nbsp;&nbsp;&nbsp;naming:</br>
&nbsp;&nbsp;&nbsp;&nbsp;physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl</br>
&nbsp;&nbsp;&nbsp;&nbsp;implicit-strategy: org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl</br>


## Database structure
languages: id(pk): INT, languageCode: VARCHAR(8)</br>
words: id(pk): INT, word: VARCHAR(255), language(fk=> languages:id)</br>
users: username(pk): VARCHAR(250), passwordEncrypted: VARCHAR(250)</br>