package uk.co.vibe.viva.shared.dto.events;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OriginationEvent {

    private String customerId;
    private String cid;
    private String from;
    private String to;

    private long callStarted;
    private long callEnded;
    private long duration;
    private long tts;
    private long asr;

    private String originationType;

    public OriginationEvent(EventsGroup group, List<String> vendorIps) {
        this.customerId = group.getCustomerId();
        this.cid = group.getCid();
        this.from = group.getOrigFrom();
        this.to = group.getOrigTo();
        this.duration = group.getIvrDuration();
        this.callStarted = group.getCreated().toEpochMilli();
        this.callEnded = group.getCompleted().toEpochMilli();
        this.tts = group.getTTSCount();
        this.asr = group.getASRCount();
        this.originationType = group.getOriginationType(vendorIps);
    }
}
