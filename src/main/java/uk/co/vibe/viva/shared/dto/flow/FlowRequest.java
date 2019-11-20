package uk.co.vibe.viva.shared.dto.flow;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import uk.co.vibe.viva.shared.dto.ivr.IvrRequest;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class FlowRequest {

    private String callId;
    private String from;
    private String to;
    private String input;
    private String action;
    private Set<String> tags;

    public FlowRequest(IvrRequest ivrRequest) {
        this.callId = ivrRequest.getCid();
        this.from = ivrRequest.getFrom();
        this.to = ivrRequest.getTo();
        if (StringUtils.isNotBlank(ivrRequest.getDigits()))
            this.input = ivrRequest.getDigits();
        if (StringUtils.isNotBlank(ivrRequest.getSpeech()))
            this.input = ivrRequest.getSpeech();
        this.action = ivrRequest.getAction();
        this.tags = ivrRequest.getTags();
    }

}
