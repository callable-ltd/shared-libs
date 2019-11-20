package uk.co.vibe.viva.shared.dto.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uk.co.vibe.viva.shared.dto.recording.RecordingUploadEvent;

import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class RecordingEvent {

    private String customerId;
    private String status;
    private String id;
    private String pid;
    private String cid;
    private String from;
    private String to;
    private String direction;
    private Set<String> extensions;
    private Set<String> pbxIds;
    private int ringDuration;
    private int callDuration;
    private long timestamp;

    public RecordingEvent(RecordingUploadEvent event) {
        this.customerId = event.getCustomer().getId();
        this.status = event.getStatus();
        this.direction = event.getDirection();
        this.id = event.getId();
        this.pid = event.getPid();
        this.cid = event.getCid();
        this.ringDuration = event.getRingDuration();
        this.callDuration = event.getCallDuration();
        this.from = event.getFrom();
        this.to = event.getTo();
        this.timestamp = System.currentTimeMillis();
        this.pbxIds = event.getPbxIds();
        this.extensions = event.getExtensions();
    }
}
