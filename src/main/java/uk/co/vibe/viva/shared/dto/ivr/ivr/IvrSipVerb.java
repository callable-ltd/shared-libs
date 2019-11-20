package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrSipVerb extends IvrResponseVerb {

    String callerId;
    String to;
    Integer timeout;
    Integer timeLimit;
    Boolean record;
    String action;

    List<IvrResponseVerb> verbs;

    @JsonCreator
    public IvrSipVerb(@JsonProperty("callerId") String callerId,
                      @JsonProperty("to") String to,
                      @JsonProperty("action") String action,
                      @JsonProperty("verbs") List<IvrResponseVerb> verbs,
                      @JsonProperty("timeout") Integer timeout,
                      @JsonProperty("timeLimit") Integer timeLimit,
                      @JsonProperty("record") Boolean record
    ) {

        this.callerId = callerId;
        this.to = to;
        this.verbs = verbs;
        this.timeout = timeout;
        this.timeLimit = timeLimit;
        this.record = record;
        this.action = action;
    }

}
