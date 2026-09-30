package model;

public interface Trackable {

    void updateStatus(String newStatus);

    String getCurrentStage();
}