# Hotel Management System

## Tech Stack

This repository is a **Java desktop application** (not a web framework project) with a MySQL backend.

- **Language:** Java  
  Evidence: Java source files under [`src/hotel`](src/hotel), e.g. [`HotelManagementSystem.java`](src/hotel/HotelManagementSystem.java).
- **UI Framework:** Java Swing/AWT  
  Evidence: Swing classes like `JFrame`, `JButton`, `JLabel` are used in [`src/hotel/HotelManagementSystem.java`](src/hotel/HotelManagementSystem.java).
- **Database:** MySQL (via JDBC)  
  Evidence: MySQL driver + JDBC URL in [`src/hotel/conn.java`](src/hotel/conn.java) (`com.mysql.jdbc.Driver`, `jdbc:mysql:///hms`).
- **Build Tooling:** Apache Ant (NetBeans project layout)  
  Evidence: [`build.xml`](build.xml), NetBeans build files in [`nbproject`](nbproject), and main class/build settings in [`nbproject/project.properties`](nbproject/project.properties).
- **External libraries configured in project properties:** MySQL Connector/J and `rs2xml`  
  Evidence: classpath entries in [`nbproject/project.properties`](nbproject/project.properties).

## Minimal Setup / Run

> These steps are based only on files present in this repository.

1. Install **JDK 22** (the project is configured with `javac.source=22` and `javac.target=22` in [`nbproject/project.properties`](nbproject/project.properties)).
2. Ensure **MySQL** is running and create database `hms` (referenced in [`src/hotel/conn.java`](src/hotel/conn.java)).
3. Update database credentials/connection in [`src/hotel/conn.java`](src/hotel/conn.java) to match your local MySQL setup.
4. Ensure classpath dependencies are available (`mysql-connector-j` and `rs2xml`) as configured in [`nbproject/project.properties`](nbproject/project.properties).
5. Build/run with Ant from the repository root:
   ```bash
   ant clean jar
   ant run
   ```

## Notes

- No migration/schema SQL files were found in the repository, so required tables must already exist in the `hms` database.
