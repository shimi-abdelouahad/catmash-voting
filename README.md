# catmash voting
Catmash voting est une implementation du kata catmash. 

## Prérequis
Il est nécessaire d'avoir un jdk 8 ou plus installé et maven. 

## Stack technique
* langage : JAVA 8, jquiry et html
* Frameworks : Spring Boot 
* Base de données : H2 DB (bdd en memoire)

## Build du projet
A la racine, faire :
```$xslt
mvn clean install
```

pour démarrer l'application, depuis la racine du projet, faites :
```$xslt
java -jar target/ catmashvoting-0.0.1-SNAPSHOT.jar
```
Avec votre navigateur chrome par exemple, allez à l'adresse http://localhost:8080/
