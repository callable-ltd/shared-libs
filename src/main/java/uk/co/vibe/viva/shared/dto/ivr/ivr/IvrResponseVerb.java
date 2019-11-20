package uk.co.vibe.viva.shared.dto.ivr.ivr;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IvrHangupVerb.class, name = "hangup"),
        @JsonSubTypes.Type(value = IvrHuntGroupVerb.class, name = "huntGroup"),
        @JsonSubTypes.Type(value = IvrRedirectVerb.class, name = "redirect"),
        @JsonSubTypes.Type(value = IvrRejectVerb.class, name = "reject"),
        @JsonSubTypes.Type(value = IvrTrunkVerb.class, name = "trunk"),
        @JsonSubTypes.Type(value = IvrTransferVerb.class, name = "transfer"),
        @JsonSubTypes.Type(value = IvrClientVerb.class, name = "client"),
        @JsonSubTypes.Type(value = IvrRemoteVerb.class, name = "remote"),
        @JsonSubTypes.Type(value = IvrDialVerb.class, name = "dial"),
        @JsonSubTypes.Type(value = IvrSipVerb.class, name = "sip"),
        @JsonSubTypes.Type(value = IvrDigitsVerb.class, name = "digits"),
        @JsonSubTypes.Type(value = IvrSpeechVerb.class, name = "speech"),
        @JsonSubTypes.Type(value = IvrSayVerb.class, name = "say"),
        @JsonSubTypes.Type(value = IvrPlayVerb.class, name = "play"),
        @JsonSubTypes.Type(value = IvrConferenceVerb.class, name = "conference"),
        @JsonSubTypes.Type(value = IvrRecordVerb.class, name = "record")
})
public abstract class IvrResponseVerb {
}
