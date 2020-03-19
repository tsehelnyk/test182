# test182

### Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#author)

### <a name="purpose"></a>Project purpose
Это простое веб-приложение, реализующее back-end и front-end часть, регистрацию 
пользователя, авторизацию пользователя, личный кабинет пользователя. При регистрации 
пользователя, сохраняется его реальный IP с которого он проходит регистрацию. 
После авторизации или регистрации пользователь попадает на страницу личного кабинета,
где отображается логин, IP, time zone пользователя. Реализована возможность сменить 
пароль, настроить часовой пояс пользователя.
<hr>

### <a name="structure"></a>Project Structure
* Java 11
* Maven 3.8.0
* Maven-checkstyle-plugin
* Spring Security
* Spring Web
* Spring Data JPA
* Spring Boot DevTools
* Thymeleaf
* H2 database
<hr>

### <a name="developer-start"></a>For developer

1. Open the project in your IDE.

2. Add it as maven project.

3. Run the project (execute method main from Test182Application class).

4. Go to [Main page](http://localhost:8080/)

5. Register users. Default user role - "USER".

6. Also, you can login as login: "admin" with password: "admin" and edit DB
by included [web-editor](http://localhost:8080/h2-console).

7. You can change admin password in application.properties file, or from 
[user account page](http://localhost:8080/user/account) like other users with USER role. 
<hr>

### <a name="author"></a>Author

https://github.com/tsehelnyk
