package dao;

import db.DatabaseConnection;
import model.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDAO {

    private String lastError = "";

    public String getLastError() {
        return lastError;
    }

    public boolean add(Application app) {

        lastError = "";

        String checkIP = "SELECT id FROM ip_records WHERE id = ?";

        String sql =
                "INSERT INTO applications " +
                "(ip_id, current_stage, last_updated, reviewer_id, remarks) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement checkStmt = conn.prepareStatement(checkIP)) {

            checkStmt.setInt(1, app.getIpId());

            try (ResultSet rs = checkStmt.executeQuery()) {

                if (!rs.next()) {

                    lastError = "IP Record ID " + app.getIpId()
                            + " does not exist.";

                    return false;
                }
            }

        } catch (SQLException e) {

            lastError = e.getMessage();
            return false;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, app.getIpId());
            stmt.setString(2, app.getCurrentStage());
            stmt.setString(3, app.getLastUpdated());

            if (app.getReviewerId() > 0) {
                stmt.setInt(4, app.getReviewerId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            stmt.setString(5, app.getRemarks());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            lastError = e.getMessage();

            System.err.println("Failed to add application.");
            e.printStackTrace();

            return false;
        }
    }

    public String getAll() {

        String sql = "SELECT * FROM applications";

        Connection conn = DatabaseConnection.getConnection();

        StringBuilder result = new StringBuilder();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                result.append(
                        "ID: " + rs.getInt("id") +
                        " | IP ID: " + rs.getInt("ip_id") +
                        " | Stage: " + rs.getString("current_stage") +
                        " | Last Updated: " + rs.getString("last_updated") +
                        " | Reviewer ID: " + rs.getObject("reviewer_id") +
                        " | Remarks: " + rs.getString("remarks")
                );

                result.append("\n");
            }

            if (result.length() == 0) {
                return "No applications found.";
            }

            return result.toString();

        } catch (SQLException e) {

            lastError = e.getMessage();

            e.printStackTrace();

            return "Failed to retrieve applications.";
        }
    }

    public boolean update(Application app) {

        lastError = "";

        String sql =
                "UPDATE applications SET " +
                "ip_id = ?, current_stage = ?, last_updated = ?, " +
                "reviewer_id = ?, remarks = ? WHERE id = ?";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, app.getIpId());
            stmt.setString(2, app.getCurrentStage());
            stmt.setString(3, app.getLastUpdated());

            if (app.getReviewerId() > 0) {
                stmt.setInt(4, app.getReviewerId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            stmt.setString(5, app.getRemarks());
            stmt.setInt(6, app.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            lastError = e.getMessage();

            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(int id) {

        lastError = "";

        String sql = "DELETE FROM applications WHERE id = ?";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            lastError = e.getMessage();

            e.printStackTrace();

            return false;
        }
    }
}