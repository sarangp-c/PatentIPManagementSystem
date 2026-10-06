# Patent & IP Management System

Java Swing + JDBC + SQLite desktop application.

## Phase 1 Status: Project Setup ✅ COMPLETE

### Folder Structure
```
PatentIPManagementSystem/
├── lib/
│   └── sqlite-jdbc-3.46.1.3.jar     (SQLite JDBC driver)
├── src/
│   ├── db/
│   │   └── DatabaseConnection.java  (singleton DB connection manager)
│   ├── model/    (empty — Phase 2)
│   ├── dao/      (empty — Phase 3)
│   ├── ui/       (empty — Phase 4)
│   └── util/     (empty)
├── bin/          (compiled .class files go here)
└── README.md
```

### How to compile & run (from project root)

Compile:
```
javac -cp lib/sqlite-jdbc-3.46.1.3.jar -d bin src/db/DatabaseConnection.java
```

Run the connection test:
```
java -cp "bin:lib/sqlite-jdbc-3.46.1.3.jar" db.DatabaseConnection
```
(On Windows, use `;` instead of `:` in the classpath: `"bin;lib/sqlite-jdbc-3.46.1.3.jar"`)

Expected output:
```
Database connection established: jdbc:sqlite:patent_ip_system.db
Schema check complete: 'users' table ready.
SUCCESS: Connection test passed.
Database connection closed.
```

This creates `patent_ip_system.db` in the project root — a real SQLite file you can
inspect with any SQLite browser (e.g. DB Browser for SQLite).

## Phase 2 Status: Database Schema + OOP Domain Models ✅ COMPLETE

### Database Schema

Implemented the SQLite database with the following tables:

- `users` — stores user information and roles.
- `ip_records` — stores Patent, Trademark and Copyright records.
- `applications` — stores application tracking information.

Foreign key relationships are implemented between `applications`, `ip_records`, and `users`.

### OOP Domain Models

Implemented the following classes:

- `IntellectualProperty` — abstract base class.
- `Patent` — extends `IntellectualProperty`.
- `Trademark` — extends `IntellectualProperty`.
- `Copyright` — extends `IntellectualProperty`.
- `Application` — represents IP application tracking.
- `Trackable` — interface for application tracking.
- `User` — represents system users.
- `Role` — represents user roles.

### OOP Concepts Used

- Abstraction
- Inheritance
- Encapsulation
- Polymorphism
- Interfaces

---
## Phase 3 Status: DAO Layer ✅ COMPLETE

### Implemented DAOs

- `IPRecordDAO` — CRUD operations for Intellectual Property records.
- `ApplicationDAO` — CRUD operations for IP applications.

### Completed Operations

- Create / Add records
- Read / Retrieve records
- Update records
- Delete records

### Testing

- IP record CRUD operations tested successfully.
- Application CRUD operations tested successfully.
- Database relationships between IP records and applications verified.
- DAO operations use JDBC with prepared statements.

### Database Integration

The DAO layer is connected to the SQLite database through the singleton `DatabaseConnection` class.

---

## Phase 4 Status: Swing GUI ✅ COMPLETE

### GUI Components

Implemented a Java Swing-based graphical user interface for the system.

The GUI includes:

- Main dashboard for navigating the system.
- IP Records management interface.
- Applications management interface.

### IP Records Management

The IP Records GUI supports:

- Adding new Patent, Trademark and Copyright records.
- Viewing all IP records.
- Updating existing IP records.
- Deleting IP records.
- Displaying record details through the GUI.

### Application Management

The Applications GUI supports:

- Adding new applications.
- Viewing all applications.
- Updating application status and details.
- Deleting applications.
- Linking applications with existing IP records.

### Database Integration

The Swing GUI is fully connected to the existing DAO and JDBC layers.


Swing GUI
    ↓
DAO Layer
    ↓
JDBC
    ↓
SQLite Database

## Phase 5 Status: Wiring + Testing ✅ COMPLETE

### System Integration

The complete system has been integrated and tested successfully.

```text
Swing GUI
    ↓
DAO Layer
    ↓
JDBC
    ↓
SQLite Database

## Additional Features

### Authentication and Role-Based Access

A login system has been implemented to provide secure access to the system.

The system supports three user roles:

- **ADMIN** – Full access to all system features.
- **RESEARCHER** – Can add, view and update IP records and view applications.
- **REVIEWER** – Can view IP records and view/update applications.

Access to different operations is controlled based on the logged-in user's role.

### User Management

An Admin-only User Management feature has been added.

The Admin can:

- View all users.
- Add new users.
- Update user details and roles.
- Delete users.
- Assign roles such as ADMIN, RESEARCHER and REVIEWER.

The system prevents the currently logged-in administrator from deleting their own account.

### Permission Feedback

The GUI provides clear feedback for restricted operations.

- Restricted actions display meaningful messages when hovered over.
- Attempting to use a restricted action displays an access warning.
- User Management is visible only to administrators.
- Role-specific permissions are applied consistently across the system.

### Logout

A logout feature has been implemented to allow users to safely return to the login screen.

### Additional Testing

The authentication and role-based access features were tested using the different user roles.

##What's next

 Dashboard with statistics	
 Search & Filter IP records
 Application status tracking	
 Application history
 Deadline / renewal reminders	
 Generate reports
 User management
 Charts / analytics	
 Document attachment system

- Admin access tested successfully.
- Researcher access tested successfully.
- Reviewer access tested successfully.
- Restricted operations tested successfully.
- User Management access tested successfully.
- Logout functionality tested successfully.
