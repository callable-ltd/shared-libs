package uk.co.vibe.viva.shared.enums;

public enum VivaEventType {
    RECORD("record-status"),
    RECORD_EVENT("record-event"),
    IVR_STATUS("ivr-status"),
    DIAL_STATUS("dial-status"),
    IVR_RESPONSE("ivr-response");

    private String type;

    VivaEventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
