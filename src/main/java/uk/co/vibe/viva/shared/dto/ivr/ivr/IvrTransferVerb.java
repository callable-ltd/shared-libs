package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrTransferVerb extends IvrResponseVerb {

    String callerId;
    String to;
    String whisper;
    Integer timeout;
    Integer timeLimit;
    Boolean record;

    @JsonCreator
    public IvrTransferVerb(@JsonProperty("callerId") String callerId,
                           @JsonProperty("to") String to,
                           @JsonProperty("whisper") String whisper,
                           @JsonProperty("timeout") Integer timeout,
                           @JsonProperty("timeLimit") Integer timeLimit,
                           @JsonProperty("record") Boolean record
    ) {

        this.callerId = callerId;
        this.to = to;
        this.whisper = whisper;
        this.timeout = timeout;
        this.timeLimit = timeLimit;
        this.record = record;
    }

}
