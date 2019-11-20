package uk.co.vibe.viva.shared.dto.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PbxAttachResponse {

    private String callId;
    private String status;
    private long attachTime;

    public PbxAttachResponse(VivaSimpleEvent event) {
        this.callId = event.getCid();
        this.status = event.getStatus();
        this.attachTime = System.currentTimeMillis();
    }
}
