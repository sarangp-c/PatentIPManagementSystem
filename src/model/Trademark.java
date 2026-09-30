package model;

public class Trademark extends IntellectualProperty {

    private String trademarkClass;

    public Trademark(
            int id,
            String title,
            String inventorName,
            String filingDate,
            String status,
            String description,
            String trademarkClass) {

        super(
                id,
                title,
                inventorName,
                filingDate,
                status,
                description
        );

        this.trademarkClass = trademarkClass;
    }

    public String getTrademarkClass() {
        return trademarkClass;
    }

    public void setTrademarkClass(String trademarkClass) {
        this.trademarkClass = trademarkClass;
    }

    @Override
    public double calculateProcessingFee() {
        return 250.0;
    }

    @Override
    public String generateReportSummary() {

        return "Trademark Report\n" +
                "Title: " + getTitle() + "\n" +
                "Owner: " + getInventorName() + "\n" +
                "Trademark Class: " + trademarkClass + "\n" +
                "Status: " + getStatus() + "\n" +
                "Processing Fee: " + calculateProcessingFee();
    }
}