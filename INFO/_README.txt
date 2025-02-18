
ТЕХСТЕК

JDK
https://www.oracle.com/java/technologies/downloads/
(якщо встановлена, то Apache Tomcat має підтягнути встановлену)

Apache Tomcat
https://tomcat.apache.org/
(якщо Apache Tomcat вже встановлено, то можна скористатися встановленим)

Spring Boot
https://spring.io/projects/spring-boot

Spring Data JPA
https://spring.io/projects/spring-data-jpa

PostgreSQL
https://www.postgresql.org/

----------------------

JAVA ПРОЕКТ

(1) Налаштовуємо БД
INFO/SQLs.sql

(2) Переходимо до Spring Initializr
https://start.spring.io/

(3) Конфігурація проекту
(цифрові позначення версій можуть
змінюватись з розвитком фреймворку):
  (a) Project: Maven
  (b) Spring Boot: обираємо стабільну версію
  (c) Project Metadata:
    Group: залишаємо так
    Artifact: Spring-Boot-Page-Sort-Rest
    Name: AppDemo
    Description: залишаємо так
    Packaging name: залишаємо так
    Packaging: War
    Java: 21
  (d) Dependencies:
    Spring Boot DevTools
    Spring Web
    Spring Data JPA
    PostgreSQL Driver

(4) Перевіряємо. Клікаємо GENERATE.

(5) Зформований zip-файл розпакуємо,
переміщуємо в папку наших проектів.

(6) Відкриваємо проект в IDE.
Досліджуємо
	src/main/
    pom.xml

(7) В src/main формуємо та структуруємо
необхідний контент.

-----------------------

РОЗГОРТАННЯ (ДЕПЛОЙ) ПРОЕКТУ

Запустимо Apache Tomcat.
Створемо секцію Git Bash.
В IDE внизу

Terminal >  <іконка-випадаючого-списку>  > Git Bash

В секції буде шлях до директорії поточного проекту

<your-base-path>/<your-project-name>

Переходимо до директорії, де розташовані файли запуску
та припинення роботи Apache Tomcat

$ cd <your-base-path>/apache-tomcat-<version>/bin

Виконуємо команду

$ ./startup.bat

УВАГА.
Запуск Apache Tomcat: startup.bat - для Windows,
startup.sh - для MacOS/Linux.
Припинення Apache Tomcat: shutdown.bat - для Windows,
shutdown.sh - для MacOS/Linux.

Окремо з'явиться інформаційне вікно,
де відображається інформація про роботу
Apache Tomcat та програми.

Можемо згорнути його.

В IDE відкриваємо бокову праворуч вкладку Maven.
Через меню вкладки відкриваємо вікно команд,
де послідовно знаходимо та подвійним кліком
запускаємо відповідні Maven-команди

mvn clean

mvn install

В директорії проекту target , знаходимо файл
Your-Project-Name-0.0.1-SNAPSHOT.war
та копіюємо його у відповідну директорію Apache Tomcat,
в файловій системі комп'ютера

<your-base-path>/apache-tomcat-<version>/webapps

Через декілька секунд, в цій директорії, має з'явитися
папка Your-Project-Name-0.0.1-SNAPSHOT.
Apache Tomcat розархівував архівний файл проекту.

Тестуємо функціонал програми.

-----------------------

РЕСУРСИ

https://www.baeldung.com/spring-boot-devtools
https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa
https://www.baeldung.com/spring-data-repositories
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
https://www.baeldung.com/spring-response-entity
https://www.baeldung.com/hibernate-identifiers
https://www.baeldung.com/spring-autowire
https://www.baeldung.com/spring-qualifier-annotation
https://www.baeldung.com/spring-data-jpa-pagination-sorting
