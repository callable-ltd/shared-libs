package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrClientVerb extends IvrResponseVerb {

    private String to;
    private List<IvrResponseVerb> verbs;

    @JsonCreator
    public IvrClientVerb(@JsonProperty("to") String to,
                         @JsonProperty("verbs") List<IvrResponseVerb> verbs) {

        this.to = to;
        this.verbs = verbs;
    }

}
