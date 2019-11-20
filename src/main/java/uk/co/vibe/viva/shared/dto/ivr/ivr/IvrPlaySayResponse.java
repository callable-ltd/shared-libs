package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IvrSayVerb.class, name = "say"),
        @JsonSubTypes.Type(value = IvrPlayVerb.class, name = "play")
})
public abstract class IvrPlaySayResponse extends IvrResponseVerb {


}
