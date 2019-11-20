package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class IvrSayVerb extends IvrPlaySayResponse {

    String text;
    Integer loop;
    String language;
    String voice;

    @JsonCreator
    public IvrSayVerb(@JsonProperty("text") String text,
                      @JsonProperty("loop") Integer loop,
                      @JsonProperty("language") String language,
                      @JsonProperty("voice") String voice) {
        this.text = text;
        this.loop = loop;
        this.language = language;
        this.voice = voice;
    }


}
