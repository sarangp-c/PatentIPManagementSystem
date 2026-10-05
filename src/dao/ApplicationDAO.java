package dao;

import db.DatabaseConnection;
import model.Application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDAO {

    public boolean add(Application app) {

        String sql =
            "INSERT INTO applications " +
            "(ip_id, current_stage, last_updated, reviewer_id, remarks) " +
            "VALUES (?, ?, ?, ?, ?)";

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

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Failed to add application.");
            e.printStackTrace();

            return false;
        }
    }

    public void getAll() {

        String sql = "SELECT * FROM applications";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    "ID: " + rs.getInt("id") +
                    " | IP ID: " + rs.getInt("ip_id") +
                    " | Stage: " + rs.getString("current_stage") +
                    " | Last Updated: " + rs.getString("last_updated") +
                    " | Reviewer ID: " + rs.getObject("reviewer_id") +
                    " | Remarks: " + rs.getString("remarks")
                );
            }

        } catch (SQLException e) {

            System.err.println("Failed to retrieve applications.");
            e.printStackTrace();
        }
    }

    public boolean update(Application app) {

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

            System.err.println("Failed to update application.");
            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM applications WHERE id = ?";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            System.err.println("Failed to delete application.");
            e.printStackTrace();

            return false;
        }
    }
}