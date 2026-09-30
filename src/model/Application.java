package model;

public class Application implements Trackable {

    private int id;
    private int ipId;
    private String currentStage;
    private String lastUpdated;
    private int reviewerId;
    private String remarks;

    public Application(
            int id,
            int ipId,
            String currentStage,
            String lastUpdated,
            int reviewerId,
            String remarks) {

        this.id = id;
        this.ipId = ipId;
        this.currentStage = currentStage;
        this.lastUpdated = lastUpdated;
        this.reviewerId = reviewerId;
        this.remarks = remarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIpId() {
        return ipId;
    }

    public void setIpId(int ipId) {
        this.ipId = ipId;
    }

    // This is BOTH the normal getter and the Trackable implementation.
    @Override
    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public void updateStatus(String newStatus) {
        this.currentStage = newStatus;
    }
}