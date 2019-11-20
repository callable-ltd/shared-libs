package uk.co.vibe.viva.shared.dto.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class DialEvent {

    private String customerId;
    private String event;
    private String status;
    private String pid;
    private String cid;
    private String from;
    private String to;
    private long timestamp;

    public DialEvent(String customerId, VivaEvent vivaEvent) {
        this.customerId = customerId;
        this.event = vivaEvent.getEvent();
        this.status = vivaEvent.getStatus();
        this.pid = vivaEvent.getPid();
        this.cid = vivaEvent.getCid();
        this.from = vivaEvent.getFrom();
        this.to = vivaEvent.getTo();
        this.timestamp = System.currentTimeMillis();
    }

    public DialEvent(String customerId, VivaSimpleEvent latest, VivaEvent vivaEvent) {
        this.customerId = customerId;
        this.event = "DialCompletionEvent";
        this.status = vivaEvent.getDialCallStatus();
        this.pid = latest.getPid();
        this.cid = latest.getCid();
        this.from = latest.getFrom();
        this.to = latest.getTo();
        this.timestamp = System.currentTimeMillis();
    }

}
