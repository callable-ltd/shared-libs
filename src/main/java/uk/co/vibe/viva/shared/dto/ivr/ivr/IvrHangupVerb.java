package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrHangupVerb extends IvrResponseVerb {

    private String reason;

    @JsonCreator
    public IvrHangupVerb(@JsonProperty("reason") String reason) {
        this.reason = reason;
    }
}
