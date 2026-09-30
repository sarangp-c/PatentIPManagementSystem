package model;

public abstract class IntellectualProperty {

    private int id;
    private String title;
    private String inventorName;
    private String filingDate;
    private String status;
    private String description;

    public IntellectualProperty(
            int id,
            String title,
            String inventorName,
            String filingDate,
            String status,
            String description) {

        this.id = id;
        this.title = title;
        this.inventorName = inventorName;
        this.filingDate = filingDate;
        this.status = status;
        this.description = description;
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and setter for inventor name
    public String getInventorName() {
        return inventorName;
    }

    public void setInventorName(String inventorName) {
        this.inventorName = inventorName;
    }

    // Getter and setter for filing date
    public String getFilingDate() {
        return filingDate;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    // Getter and setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Abstract methods
    public abstract double calculateProcessingFee();

    public abstract String generateReportSummary();
}