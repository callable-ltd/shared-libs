package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrConferenceVerb extends IvrResponseVerb {

    private String name;
    private String url;
    private Boolean mute;
    private String action;

    @JsonCreator
    public IvrConferenceVerb(@JsonProperty("name") String name,
                             @JsonProperty("url") String url,
                             @JsonProperty("action") String action,
                             @JsonProperty("mute") Boolean mute) {
        this.name = name;
        this.url = url;
        this.action = action;
        this.mute = mute;
    }

}
