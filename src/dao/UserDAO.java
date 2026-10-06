package dao;

import db.DatabaseConnection;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private String lastError = "";

    public String getLastError() {
        return lastError;
    }

    public User login(String username, String password) {

        lastError = "";

        String sql =
                "SELECT id, username, password, role " +
                "FROM users WHERE username = ? AND password = ?";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            Role.valueOf(rs.getString("role"))
                    );
                }
            }

        } catch (SQLException e) {

            lastError = e.getMessage();
            e.printStackTrace();

        } catch (IllegalArgumentException e) {

            lastError = "Invalid role in database.";
        }

        return null;
    }

    public boolean createUser(User user) {

        lastError = "";

        String sql =
                "INSERT INTO users (username, password, role) " +
                "VALUES (?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().name());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            lastError = e.getMessage();
            return false;
        }
    }

    public String getAllUsers() {

        lastError = "";

        String sql =
                "SELECT id, username, password, role " +
                "FROM users ORDER BY id";

        Connection conn = DatabaseConnection.getConnection();

        StringBuilder result = new StringBuilder();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                result.append(
                        "ID: " + rs.getInt("id")
                                + " | Username: "
                                + rs.getString("username")
                                + " | Password: "
                                + rs.getString("password")
                                + " | Role: "
                                + rs.getString("role")
                                + "\n"
                );
            }

            if (result.length() == 0) {
                return "No users found.";
            }

            return result.toString();

        } catch (SQLException e) {

            lastError = e.getMessage();
            return "Failed to retrieve users.";
        }
    }

    public boolean updateUser(User user) {

        lastError = "";

        String sql =
                "UPDATE users SET username = ?, " +
                "password = ?, role = ? WHERE id = ?";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().name());
            stmt.setInt(4, user.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            lastError = e.getMessage();
            return false;
        }
    }

    public boolean deleteUser(int id) {

        lastError = "";

        String sql =
                "DELETE FROM users WHERE id = ?";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            lastError = e.getMessage();
            return false;
        }
    }

    public boolean userExists(String username) {

        String sql =
                "SELECT id FROM users WHERE username = ?";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {

            lastError = e.getMessage();
            return false;
        }
    }

    public void createDefaultUsers() {

        if (!userExists("admin")) {

            createUser(
                    new User(
                            0,
                            "admin",
                            "admin123",
                            Role.ADMIN
                    )
            );
        }

        if (!userExists("researcher")) {

            createUser(
                    new User(
                            0,
                            "researcher",
                            "research123",
                            Role.RESEARCHER
                    )
            );
        }

        if (!userExists("reviewer")) {

            createUser(
                    new User(
                            0,
                            "reviewer",
                            "review123",
                            Role.REVIEWER
                    )
            );
        }
    }
}