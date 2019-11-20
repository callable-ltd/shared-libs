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
public class IvrDigitsVerb extends IvrResponseVerb implements IvrGatherVerb {

    private String type = "digits";
    private String finishOnKey;
    private Integer numDigits;
    private Integer timeout;
    private String action;
    private List<IvrPlaySayResponse> verbs;

    @JsonCreator
    public IvrDigitsVerb(@JsonProperty("finishOnKey") String finishOnKey,
                         @JsonProperty("numDigits") Integer numDigits,
                         @JsonProperty("timeout") Integer timeout,
                         @JsonProperty("action") String action,
                         @JsonProperty("verbs") List<IvrPlaySayResponse> verbs
    ) {
        this.finishOnKey = finishOnKey;
        this.numDigits = numDigits;
        this.timeout = timeout;
        this.action = action;
        this.verbs = verbs;
    }
}
