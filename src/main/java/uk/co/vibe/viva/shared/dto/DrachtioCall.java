package uk.co.vibe.viva.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DrachtioCall {

    @JsonProperty("service_url")
    private String serviceUrl;

    private String direction;

    @JsonProperty("call_sid")
    private String callSid;

    @JsonProperty("parent_call_sid")
    private String parentCallSid;

    @JsonProperty("application_sid")
    private String applicationSid;

    private String from;

    private String to;

    @JsonProperty("caller_id")
    private String callerId;

    @JsonProperty("call_id")
    private String callId;

    @JsonProperty("call_status")
    private String callStatus;

    @JsonProperty("sip_status")
    private Integer sipStatus;

    private Integer duration;

}
