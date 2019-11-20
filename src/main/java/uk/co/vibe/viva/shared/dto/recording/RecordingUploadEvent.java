package uk.co.vibe.viva.shared.dto.recording;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.events.EventsGroup;
import uk.co.vibe.viva.shared.dto.events.VivaSimpleEvent;
import uk.co.vibe.viva.shared.dto.pbx.SimplePBXEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@ToString
@NoArgsConstructor
public class RecordingUploadEvent {

    private CustomerDTO customer;
    private String status;
    private String id;
    private String cid;
    private String pid;
    private String from;
    private String to;
    private String url;
    private String ip;
    private Integer ringDuration;
    private Integer callDuration;
    private String direction;
    private Set<String> tags;
    private Long duration;

    private Set<String> pbxIds;

    private Set<String> extensions;

    public RecordingUploadEvent(CustomerDTO customer, VivaSimpleEvent event, EventsGroup group) {
        this.status = "uploading";
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.cid = event.getDialCid();
        this.pid = event.getParentId();
        this.from = event.getFrom();
        this.to = event.getTo();
        this.url = event.getDialRecordingUrl();
        this.ip = event.getIp();
        this.ringDuration = event.getDialRingDuration();
        this.callDuration = event.getDialCallDuration();
        this.duration = group.getRecordingDuration(event.getDialCid());
        this.direction = event.getDirection();
        this.tags = new HashSet<>();
        this.pbxIds = group.getPbxIds();
        this.extensions = group.getPbxEvents().stream()
                .filter(pbxEvent -> pbxEvent.getEvent().equals("connected"))
                .filter(pbxEvent -> pbxEvent.getComponent().equals("phone"))
                .map(SimplePBXEvent::getExtension)
                .collect(Collectors.toSet());
    }
}
