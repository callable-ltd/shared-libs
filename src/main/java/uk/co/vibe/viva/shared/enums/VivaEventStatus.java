package uk.co.vibe.viva.shared.enums;

public enum VivaEventStatus {
    INITIATED("initiated"),
    TRYING("trying"),
    RINGING("ringing"),
    ANSWERED("answered"),
    EARLY_MEDIA("early-media"),
    IN_PROGRESS("in-progress"),
    FAILED("failed"),
    BUSY("busy"),
    COMPLETED("completed");

    private String status;

    VivaEventStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
