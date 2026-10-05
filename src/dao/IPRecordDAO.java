package dao;

import db.DatabaseConnection;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IPRecordDAO {

    public boolean add(IntellectualProperty ip) {

        String sql =
            "INSERT INTO ip_records " +
            "(type, title, inventor_name, filing_date, status, description, sub_type_value) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String type;
        String subType;

        if (ip instanceof Patent) {
            type = "Patent";
            subType = ((Patent) ip).getPatentCategory();

        } else if (ip instanceof Trademark) {
            type = "Trademark";
            subType = ((Trademark) ip).getTrademarkClass();

        } else if (ip instanceof Copyright) {
            type = "Copyright";
            subType = ((Copyright) ip).getWorkType();

        } else {
            return false;
        }

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            stmt.setString(2, ip.getTitle());
            stmt.setString(3, ip.getInventorName());
            stmt.setString(4, ip.getFilingDate());
            stmt.setString(5, ip.getStatus());
            stmt.setString(6, ip.getDescription());
            stmt.setString(7, subType);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.err.println("Failed to add IP record.");
            e.printStackTrace();

            return false;
        }
    }

    public void getAll() {

        String sql = "SELECT * FROM ip_records";

        Connection conn = DatabaseConnection.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                System.out.println(
                    "ID: " + rs.getInt("id") +
                    " | Type: " + rs.getString("type") +
                    " | Title: " + rs.getString("title") +
                    " | Inventor: " + rs.getString("inventor_name") +
                    " | Status: " + rs.getString("status") +
                    " | Sub-type: " + rs.getString("sub_type_value")
                );
            }

        } catch (SQLException e) {

            System.err.println("Failed to retrieve IP records.");
            e.printStackTrace();
        }
    }
    public boolean update(IntellectualProperty ip) {

    String sql =
        "UPDATE ip_records SET " +
        "type = ?, title = ?, inventor_name = ?, " +
        "filing_date = ?, status = ?, description = ?, " +
        "sub_type_value = ? WHERE id = ?";

    String type;
    String subType;

    if (ip instanceof Patent) {
        type = "Patent";
        subType = ((Patent) ip).getPatentCategory();

    } else if (ip instanceof Trademark) {
        type = "Trademark";
        subType = ((Trademark) ip).getTrademarkClass();

    } else if (ip instanceof Copyright) {
        type = "Copyright";
        subType = ((Copyright) ip).getWorkType();

    } else {
        return false;
    }

    Connection conn = DatabaseConnection.getConnection();

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, type);
        stmt.setString(2, ip.getTitle());
        stmt.setString(3, ip.getInventorName());
        stmt.setString(4, ip.getFilingDate());
        stmt.setString(5, ip.getStatus());
        stmt.setString(6, ip.getDescription());
        stmt.setString(7, subType);
        stmt.setInt(8, ip.getId());

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {

        System.err.println("Failed to update IP record.");
        e.printStackTrace();

        return false;
    }
}
public boolean delete(int id) {

    String sql = "DELETE FROM ip_records WHERE id = ?";

    Connection conn = DatabaseConnection.getConnection();

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);

        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {

        System.err.println("Failed to delete IP record.");
        e.printStackTrace();

        return false;
    }
}
}