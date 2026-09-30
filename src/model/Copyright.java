package model;

public class Copyright extends IntellectualProperty {

    private String workType;

    public Copyright(
            int id,
            String title,
            String inventorName,
            String filingDate,
            String status,
            String description,
            String workType) {

        super(
                id,
                title,
                inventorName,
                filingDate,
                status,
                description
        );

        this.workType = workType;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    @Override
    public double calculateProcessingFee() {
        return 150.0;
    }

    @Override
    public String generateReportSummary() {

        return "Copyright Report\n" +
                "Title: " + getTitle() + "\n" +
                "Creator: " + getInventorName() + "\n" +
                "Work Type: " + workType + "\n" +
                "Status: " + getStatus() + "\n" +
                "Processing Fee: " + calculateProcessingFee();
    }
}