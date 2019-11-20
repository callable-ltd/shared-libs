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
public class IvrHuntGroupVerb extends IvrResponseVerb {

    private String type = "huntGroup";
    private String callerId;
    private Integer timeout;
    private Integer timeLimit;
    private Boolean record;
    private String action;

    private List<IvrResponseVerb> verbs;

    @JsonCreator
    public IvrHuntGroupVerb(@JsonProperty("callerId") String callerId,
                            @JsonProperty("action") String action,
                            @JsonProperty("verbs") List<IvrResponseVerb> verbs,
                            @JsonProperty("timeout") Integer timeout,
                            @JsonProperty("timeLimit") Integer timeLimit,
                            @JsonProperty("record") Boolean record
    ) {

        this.callerId = callerId;
        this.verbs = verbs;
        this.timeout = timeout;
        this.timeLimit = timeLimit;
        this.record = record;
        this.action = action;
    }

}
