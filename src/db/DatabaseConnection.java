package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton utility class that manages the single shared JDBC connection
 * to the SQLite database file for the whole application.
 *
 * Why singleton?
 * Swing apps typically run within one JVM, and SQLite works well with
 * a reused connection for this project.
 */
public class DatabaseConnection {

    // Name of the SQLite database file
    private static final String DB_URL = "jdbc:sqlite:patent_ip_system.db";

    // Single shared database connection
    private static Connection connection = null;

    // Private constructor prevents object creation from outside
    private DatabaseConnection() {
    }

    /**
     * Returns the single shared Connection object.
     * Creates the database file automatically if it does not exist.
     */
    public static Connection getConnection() {

        if (connection == null) {

            try {

                // Load SQLite JDBC driver
                Class.forName("org.sqlite.JDBC");

                // Establish connection
                connection = DriverManager.getConnection(DB_URL);

                System.out.println(
                        "Database connection established: " + DB_URL
                );

                // Enable SQLite foreign-key enforcement
                try (Statement pragmaStatement = connection.createStatement()) {
                    pragmaStatement.execute("PRAGMA foreign_keys = ON");
                }

                // Create required tables
                initializeSchema();

            } catch (ClassNotFoundException e) {

                System.err.println(
                        "SQLite JDBC driver not found. " +
                        "Make sure the SQLite JDBC .jar is in the classpath."
                );

                e.printStackTrace();

            } catch (SQLException e) {

                System.err.println(
                        "Failed to connect to the database."
                );

                e.printStackTrace();
            }
        }

        return connection;
    }

    /**
     * Creates all required database tables if they do not already exist.
     */
    private static void initializeSchema() {

        // ---------------------------------------------------------
        // USERS TABLE
        // ---------------------------------------------------------

        String createUsersTable =
                "CREATE TABLE IF NOT EXISTS users (" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    username TEXT NOT NULL UNIQUE," +
                "    password TEXT NOT NULL," +
                "    role TEXT NOT NULL" +
                ");";

        // ---------------------------------------------------------
        // IP RECORDS TABLE
        // ---------------------------------------------------------

        String createIPRecordsTable =
        "CREATE TABLE IF NOT EXISTS ip_records (" +
        "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
        "    type TEXT NOT NULL," +
        "    title TEXT NOT NULL," +
        "    inventor_name TEXT NOT NULL," +
        "    filing_date TEXT NOT NULL," +
        "    status TEXT NOT NULL," +
        "    description TEXT," +
        "    sub_type_value TEXT" +
        ");";

        // ---------------------------------------------------------
        // APPLICATIONS TABLE
        // ---------------------------------------------------------

        String createApplicationsTable =
                "CREATE TABLE IF NOT EXISTS applications (" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    ip_id INTEGER NOT NULL," +
                "    current_stage TEXT NOT NULL," +
                "    last_updated TEXT NOT NULL," +
                "    reviewer_id INTEGER," +
                "    remarks TEXT," +
                "    FOREIGN KEY (ip_id) REFERENCES ip_records(id)," +
                "    FOREIGN KEY (reviewer_id) REFERENCES users(id)" +
                ");";

        // ---------------------------------------------------------
        // EXECUTE TABLE CREATION
        // ---------------------------------------------------------

        try (Statement stmt = connection.createStatement()) {

            stmt.execute(createUsersTable);

            stmt.execute(createIPRecordsTable);

            stmt.execute(createApplicationsTable);

            System.out.println(
                    "Schema check complete: all tables ready."
            );

        } catch (SQLException e) {

            System.err.println(
                    "Failed to initialize schema."
            );

            e.printStackTrace();
        }
    }

    /**
     * Closes the database connection.
     */
    public static void closeConnection() {

        if (connection != null) {

            try {

                connection.close();

                connection = null;

                System.out.println(
                        "Database connection closed."
                );

            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    /**
     * Manual test method.
     *
     * Run this class directly to verify:
     * 1. JDBC connection
     * 2. SQLite database creation
     * 3. Database schema creation
     */
    public static void main(String[] args) {

        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {

            System.out.println(
                    "SUCCESS: Connection test passed."
            );

        } else {

            System.out.println(
                    "FAILURE: Connection test failed."
            );
        }

        DatabaseConnection.closeConnection();
    }
}