package uk.co.vibe.viva.shared.dto.callmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RcResponse {
    private String sid;
    private String instanceId;
    private String accountSid;
    private String to;
    private String from;
    private String status;
    private String callerName;
}
