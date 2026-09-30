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

## What's Next
- Phase 2: Database schema (ip_records, applications tables) + OOP domain models
- Phase 3: DAO layer
- Phase 4: Swing GUI
- Phase 5: Wiring + testing
