package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class IvrRejectVerb extends IvrResponseVerb {

    String reason;

    @JsonCreator
    public IvrRejectVerb(@JsonProperty("reason") String reason) {
        this.reason = reason;
    }

}
