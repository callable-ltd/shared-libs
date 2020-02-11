package uk.co.vibe.viva.shared.dto.recording;

import lombok.Data;

@Data
//todo
public class RecordingMetadata {
    private String callSid;
    private String callId;
    private String parentCallSid;

    //Audio props
    private int sampleRate;
    private String mixType;

    private String direction;
    private String accountSid;
    private String applicationSid;
    private String callerId;
    private String from;
    private String to;
    private String callStatus;
    private String sipStatus;

    private String bucket;
    private String key;

}
