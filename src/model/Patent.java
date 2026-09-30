package model;

public class Patent extends IntellectualProperty {

    private String patentCategory;

    public Patent(
            int id,
            String title,
            String inventorName,
            String filingDate,
            String status,
            String description,
            String patentCategory) {

        super(
                id,
                title,
                inventorName,
                filingDate,
                status,
                description
        );

        this.patentCategory = patentCategory;
    }

    public String getPatentCategory() {
        return patentCategory;
    }

    public void setPatentCategory(String patentCategory) {
        this.patentCategory = patentCategory;
    }

    @Override
    public double calculateProcessingFee() {
        return 500.0;
    }

    @Override
    public String generateReportSummary() {

        return "Patent Report\n" +
                "Title: " + getTitle() + "\n" +
                "Inventor: " + getInventorName() + "\n" +
                "Category: " + patentCategory + "\n" +
                "Status: " + getStatus() + "\n" +
                "Processing Fee: " + calculateProcessingFee();
    }
}