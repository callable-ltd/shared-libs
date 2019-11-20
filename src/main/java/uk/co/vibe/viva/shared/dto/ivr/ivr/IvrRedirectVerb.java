package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrRedirectVerb extends IvrResponseVerb {

    String url;

    @JsonCreator
    public IvrRedirectVerb(@JsonProperty("url") String url) {
        this.url = url;
    }

}
