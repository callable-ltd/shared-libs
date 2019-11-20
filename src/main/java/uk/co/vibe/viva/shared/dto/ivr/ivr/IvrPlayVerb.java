package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrPlayVerb extends IvrPlaySayResponse {

    String url;
    Integer loop;

    @JsonCreator
    public IvrPlayVerb(@JsonProperty("url") String url,
                       @JsonProperty("loop") Integer loop) {
        this.url = url;
        this.loop = loop;
    }

}
