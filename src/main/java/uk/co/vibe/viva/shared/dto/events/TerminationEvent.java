package uk.co.vibe.viva.shared.dto.events;

import lombok.Data;
import lombok.ToString;
import uk.co.vibe.viva.shared.dto.customer.DdiDTO;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import java.util.List;

@Data
@ToString
public class TerminationEvent {

    String customerId;
    String pid;
    String cid;
    String from;
    String to;

    long callStarted;
    long callEnded;
    long ringDuration;
    long callDuration;
    long recordedDuration;

    String terminationType;

    public TerminationEvent(EventsGroup group, String cid) {
        this.customerId = group.getCustomerId();
        this.cid = cid;
        this.pid = group.getCid();
        this.from = group.getTermFrom(cid);
        this.to = group.getTermTo(cid);
        this.callDuration = group.getDialDuration(cid);
        this.ringDuration = group.getRingDuration(cid);
        this.recordedDuration = group.getRecordingDuration(cid);
        this.callStarted = group.getCreated(cid).toEpochMilli();
        this.callEnded = group.getCompleted(cid).toEpochMilli();
        this.terminationType = group.getTerminationType(cid);
    }
}
