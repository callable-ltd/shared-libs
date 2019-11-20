package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrSpeechVerb extends IvrResponseVerb implements IvrGatherVerb {

    Integer timeout;
    String hints;
    String language;
    String action;

    List<IvrPlaySayResponse> verbs;

    @JsonCreator
    public IvrSpeechVerb(@JsonProperty("hints") String hints,
                         @JsonProperty("timeout") Integer timeout,
                         @JsonProperty("language") String language,
                         @JsonProperty("action") String action,
                         @JsonProperty("verbs") List<IvrPlaySayResponse> verbs
    ) {
        this.hints = hints;
        this.timeout = timeout;
        this.language = language;
        this.action = action;
        this.verbs = verbs;
    }

}
