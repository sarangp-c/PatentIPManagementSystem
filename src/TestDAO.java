import dao.ApplicationDAO;
import model.Application;

public class TestDAO {

    public static void main(String[] args) {

        ApplicationDAO dao = new ApplicationDAO();

        System.out.println("--- BEFORE UPDATE ---");
        dao.getAll();

        Application app = new Application(
                1,
                1,
                "Approved",
                "2026-10-05",
                0,
                "Application approved."
        );

        if (dao.update(app)) {
            System.out.println("\nApplication updated successfully!");
        }

        System.out.println("\n--- AFTER UPDATE ---");
        dao.getAll();

        if (dao.delete(1)) {
            System.out.println("\nApplication deleted successfully!");
        }

        System.out.println("\n--- AFTER DELETE ---");
        dao.getAll();
    }
}