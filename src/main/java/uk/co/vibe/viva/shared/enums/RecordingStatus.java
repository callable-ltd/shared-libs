package uk.co.vibe.viva.shared.enums;

public enum RecordingStatus {
    RECORDING("recording"),
    UPLOADED("uploaded"),
    COMPLETED("completed");

    private String status;

    RecordingStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
