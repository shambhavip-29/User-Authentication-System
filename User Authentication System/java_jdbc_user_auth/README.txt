
User Registration / Login / Forgot Password
Java + JDBC + MySQL Project

Requirements:
- Java 8+
- MySQL Server
- MySQL Connector/J (jdbc driver) — place jar inside /lib

Setup Steps:

1) Create database & table
   - Open MySQL and run:  mysql -u root -p < db_schema.sql

2) Update DB credentials in:
   src/DB.java
   (DB_URL / USER / PASSWORD)

3) Add JDBC Driver
   - Download MySQL Connector/J
   - Copy mysql-connector-j.jar into /lib folder

4) Compile
   Windows:
     javac -cp ".;lib/*" -d bin src/*.java
   Linux/Mac:
     javac -cp ".:lib/*" -d bin src/*.java

5) Run (menu-driven program)
   Windows:
     java -cp "bin;lib/*" Main
   Linux/Mac:
     java -cp "bin:lib/*" Main

Features implemented (per acceptance criteria):
- User Registration with duplicate email validation
- Input validation + password rule (8+ chars, letters + numbers)
- Password hashing using SHA‑256 (no plain text storage)
- Login with hashed password verification
- Forgot password (set new password if email exists — no email leak)
- Confirmation messages for each operation
