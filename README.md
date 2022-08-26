
# Библиотека: Читатели и Книги

Демо библиотеки, где вы можете создавать/редактировать/удалять читателей,
                        создавать/редактировать/удалять книги, смотреть подробную информацию о читателе или книге, "привязывать" (выдавать)
                        книгу читателю и "отвязывать" (возвращать) книгу.




                        


## Для работы понадобится

- Intellij IDEA и Apache Tomcat (веб-сервер для выполнения проекта)
- PostgreSQL (чтобы выполнить запросы из schema.sql и создать таблицы/записи)
- Браузер (чтобы открыть http://localhost:8080)
## Подготовка к работе
- установить Postgres 14, подключиться, создать БД project1
- оставить Username как postgres, а password как 123
- проверить, чтобы созданная вами БД и данные (логин, пароль) совпадали с указанными в файле проекта "database.properties"
- выполнить schema.sql (через pgAdmin или через Intellij IDEA, раздел "Database") для создания таблиц и наполнения их демо-данными
## Запуск/работа
- запустить проект из Intellij IDEA
- зайти через браузер на http://localhost:8080
- просматривать/создавать Читателей и Книги
- выдавать/забирать Книги
## Технологии
- spring-core, spring-context, spring-web, spring-jdbc, spring-webmvc
- servlet-api, anotation-api
- hibernate-validator
- PostgreSQL
- Junit 
- Thymeleaf
- Bootstrap
- HTML, CSS
- Apache Tomcat
- Intellij IDEA
- Maven
## Автор

- [@Arhat161](https://github.com/Arhat161)


