# Hotel Management System

This repository contains a **Java desktop application** for hotel operations (customer check-in/check-out, room updates, employee/driver info, etc.).

## Tech Stack

| Layer | Technology | Evidence in repo |
|---|---|---|
| Language | Java | `src/hotel/*.java` |
| UI framework | Java Swing + AWT | `import javax.swing.*; import java.awt.*;` across UI classes like `src/hotel/HotelManagementSystem.java` and `src/hotel/Dashboard.java` |
| Database access | JDBC | `src/hotel/conn.java` (`DriverManager.getConnection(...)`, `java.sql.*`) |
| Database | MySQL (`hms` schema) | `src/hotel/conn.java` (`jdbc:mysql:///hms`) |
| Build tool | Apache Ant (NetBeans project) | `build.xml`, `nbproject/build-impl.xml` |
| Project type | NetBeans Java SE project (`hms`) | `nbproject/project.xml` |
| External libraries | MySQL Connector/J, rs2xml (`DbUtils`) | `nbproject/project.properties` (`mysql-connector-j`, `rs2xml.jar`) and imports like `net.proteanit.sql.DbUtils` in `src/hotel/Room.java` |

## Setup and Run

### Prerequisites

- JDK (project is configured with `javac.source=22` / `javac.target=22` in `nbproject/project.properties`)
- Apache Ant
- MySQL server
- MySQL Connector/J JAR
- `rs2xml.jar` (used for `DbUtils`)

### 1) Configure database

The app connects using `src/hotel/conn.java`:

- URL: `jdbc:mysql:///hms`
- Username: `root`
- Password: `Sanju123#`

Create the `hms` database in MySQL and update credentials in `conn.java` if your local setup differs.

### 2) Configure dependencies

This NetBeans project expects MySQL and rs2xml JARs on the classpath (see `nbproject/project.properties`).

If paths differ on your machine, update the referenced JAR locations in project settings / classpath.

### 3) Build and run

From the repository root:

```bash
ant clean
ant run
```

Main class: `hotel.HotelManagementSystem` (defined in `nbproject/project.properties`).

## Notes

- This is a desktop GUI project, not a REST API service.
- No automated tests are currently present in the repository.
