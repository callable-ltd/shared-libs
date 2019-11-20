package uk.co.vibe.viva.shared.dto.events;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import uk.co.vibe.viva.shared.dto.ApiResponse;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrResponseVerb;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VivaEvent {

    //ivr-request,ivr-response,ivr-status
    //dial-request,dial-response,dial-status
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
    private CustomerDTO customerDTO;
    private ApiResponse<IvrResponseVerb> ivrResponse;
    private long timestamp;

    public String getParentId() {
        return StringUtils.isNotBlank(pid) ? pid : cid;
    }

}
