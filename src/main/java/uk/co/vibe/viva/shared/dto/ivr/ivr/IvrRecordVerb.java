package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
public class IvrRecordVerb extends IvrResponseVerb {

    private String type = "record";
    private String action;
    private Integer timeout;
    private String finishOnKey;
    private Integer maxLength;
    private Boolean playBeep;
    private Boolean transcribe;

    @JsonCreator
    public IvrRecordVerb(@JsonProperty("action") String action,
                         @JsonProperty("timeout") Integer timeout,
                         @JsonProperty("finishOnKey") String finishOnKey,
                         @JsonProperty("maxLength") Integer maxLength,
                         @JsonProperty("playBeep") Boolean playBeep,
                         @JsonProperty("transcribe") Boolean transcribe

    ) {

        this.action = action;
        this.timeout = timeout;
        this.finishOnKey = finishOnKey;
        this.maxLength = maxLength;
        this.playBeep = playBeep;
        this.transcribe = transcribe;
    }

}
