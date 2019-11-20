package uk.co.vibe.viva.shared.dto.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import uk.co.vibe.viva.shared.dto.ApiResponse;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrResponseVerb;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrSipVerb;

import java.util.Optional;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VivaSimpleEvent {

    private String type;
    private String event;
    private String pid;
    private String cid;
    private String status;
    private String from;
    private String to;
    private String digits;
    private String speech;
    private String dialCid;
    private String dialCallStatus;
    private String ip;
    private Integer dialCallDuration;
    private Long recordingMS;
    private Integer dialRingDuration;
    private String dialRecordingUrl;
    private String dialPublicRecordingId;
    private String dialPublicRecordingURL;
    private String direction;
    private ApiResponse<IvrResponseVerb> ivrResponse;
    private long timestamp;

    public VivaSimpleEvent(VivaEvent event) {
        this.type = event.getType();
        this.event = event.getEvent();
        this.pid = event.getPid();
        this.cid = event.getCid();
        this.status = event.getStatus();
        this.from = event.getFrom();
        this.to = event.getTo();
        this.digits = event.getDigits();
        this.speech = event.getSpeech();
        this.dialCid = event.getDialCid();
        this.dialCallStatus = event.getDialCallStatus();
        this.ip = event.getIp();
        this.dialCallDuration = event.getDialCallDuration();
        this.recordingMS = event.getRecordingMS();
        this.dialRingDuration = event.getDialRingDuration();
        this.dialRecordingUrl = event.getDialRecordingUrl();
        this.dialPublicRecordingId = event.getDialPublicRecordingId();
        this.dialPublicRecordingURL = event.getDialPublicRecordingURL();
        this.ivrResponse = event.getIvrResponse();
        this.timestamp = event.getTimestamp();
        this.direction = Optional.ofNullable(event.getIvrResponse()).map(ivrResp -> ivrResp.getItems().stream()
                .anyMatch(ivrResponseVerb -> {
                    if (ivrResponseVerb instanceof IvrSipVerb) {
                        return ((IvrSipVerb) ivrResponseVerb).getTo().contains(event.getIp());
                    }
                    return false;
                }) ? "outbound" : "inbound")
                .orElse(null);
    }

    public String getParentId() {
        return StringUtils.isNotBlank(pid) ? pid : cid;
    }

    private boolean matchesDialCid(String id) {
        return Optional.ofNullable(dialCid)
                .filter(s -> s.equals(id))
                .isPresent();
    }

    public boolean isRecordedIvrResponse(String id) {
        return matchesDialCid(id) &&
                type.equals("ivr-response") &&
                StringUtils.isNotBlank(dialRecordingUrl);
    }

}
