package uk.co.vibe.viva.shared.dto.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uk.co.vibe.viva.shared.dto.ApiResponse;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrResponseVerb;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class FlowEvent {

    private String customerId;
    private String event;
    private String status;
    private String pid;
    private String cid;
    private String from;
    private String to;
    private String speech;
    private String digits;
    private List<IvrResponseVerb> instructions;
    private long timestamp;

    public FlowEvent(String customerId, VivaEvent vivaEvent) {
        this.customerId = customerId;
        this.event = vivaEvent.getEvent();
        this.status = vivaEvent.getStatus();
        this.pid = vivaEvent.getPid();
        this.cid = vivaEvent.getCid();
        this.from = vivaEvent.getFrom();
        this.to = vivaEvent.getTo();
        this.speech = vivaEvent.getSpeech();
        this.digits = vivaEvent.getDigits();
        this.instructions = vivaEvent.getIvrResponse().getItems();
        this.timestamp = System.currentTimeMillis();
    }
}
