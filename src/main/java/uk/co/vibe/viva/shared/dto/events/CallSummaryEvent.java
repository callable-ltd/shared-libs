package uk.co.vibe.viva.shared.dto.events;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CallSummaryEvent {

    String customerId;
    String cid;

    long callStarted;
    long callEnded;

    int callLegs;
    long ivrDuration;
    long recordedDuration;
    long callDuration;
    long ringDuration;

    public CallSummaryEvent(EventsGroup group) {
        this.customerId = group.getCustomerId();
        this.cid = group.getCid();
        this.callLegs = group.getDialLegCount();
        this.recordedDuration = group.getRecordingDuration(cid);
        this.callDuration = group.getDialDuration(null);
        this.ringDuration = group.getRingDuration(null);
        this.callStarted = group.getCreated().toEpochMilli();
        this.callEnded = group.getCompleted().toEpochMilli();
        this.ivrDuration = group.getIvrDuration();
    }
}
