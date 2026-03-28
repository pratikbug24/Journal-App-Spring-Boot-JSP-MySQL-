📓 Journal App (Spring Boot + JSP + MySQL)

A full-stack Journal Management Application built using Spring Boot, JSP, and MySQL.
Users can register, log in, and manage their personal journal entries.

------------

🚀 Features

📝 Create, view, and delete journal entries

👤 User registration and login system

🔗 REST API + JSP-based UI

💾 MySQL database integration (via JPA/Hibernate)

🎨 Clean frontend using HTML, CSS, and JavaScript

-----------

🛠️ Tech Stack

Backend
- Java 17

* Spring Boot 2.7

- Spring Data JPA (Hibernate)

Frontend
- HTML5

- CSS3

- JavaScript (Fetch API)

- JSP (Java Server Pages)

Database

- MySQL (phpMyAdmin)
---------


📁 Project Structure
```
journalApp/
│
├── src/main/java/net/engineeringdigest/journalApp/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│
├── src/main/webapp/WEB-INF/jsp/
│   ├── index.jsp
│   ├── add.jsp
│
├── src/main/resources/
│   └── application.properties
│
├── pom.xml
```

-----------------
📦 API Endpoints
```
Journal APIs
GET    /api/journal        → Get all entries
POST   /api/journal        → Create entry
DELETE /api/journal/{id}   → Delete entry
```
-------
⚙️ Setup Instructions

1️⃣ Clone Repository
```
git clone https://github.com/your-username/journalApp.git
cd journalApp
```
---------
2️⃣ Configure Database

Open phpMyAdmin and create a database:
```
CREATE DATABASE journaldb;
```
------
3️⃣ Update application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/journaldb
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4️⃣ Build Project
```
mvn clean install
```
5️⃣ Run Application
```
mvn spring-boot:run
```
OR
```
java -jar target/journalApp-0.0.1-SNAPSHOT.jar
```
--------

🧠 How It Works

- User interacts via JSP or frontend UI
- Request goes to Spring Boot Controller
- Service layer processes logic
- Repository communicates with database using JPA
- Hibernate converts Java objects → SQL queries

-------------------

🤝 Contributing

Feel free to fork this repo and improve the project!

--------
⭐ If you like this project, give it a star!
