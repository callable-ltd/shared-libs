package uk.co.vibe.viva.shared.dto.ivr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import uk.co.vibe.viva.shared.dto.customer.CallbackURLDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IvrRequest {

    @NotNull
    @NotBlank
    private String action;

    private String pid;

    @NotNull
    @NotBlank
    private String cid;

    @NotNull
    @NotBlank
    private String ip;

    @NotNull
    @NotBlank
    private String status;

    @NotNull
    @NotBlank
    private String from;

    @NotNull
    @NotBlank
    private String to;

    private String digits;

    private String speech;

    private String dialCid;

    private String dialCallStatus;

    private Integer dialCallDuration;

    private Integer dialRingDuration;

    private String dialRecordingUrl;

    private String referTarget;

    private CallbackURLDTO callback;

    private Boolean privacy;

    private String pAsserted;

    private Set<String> tags;

    private String recordingUrl;
    private String recordingDuration;
    private String transcriptionText;
    private String transcriptionStatus;
    private String transcriptionUrl;


    public boolean isHangup() {
        return action.equals("hangup");
    }

    public boolean isFailed() {
        return Optional.ofNullable(dialCallStatus)
                .map(s -> s.equals("failed"))
                .orElse(Boolean.FALSE);
    }

    public boolean isBusy() {
        return Optional.ofNullable(dialCallStatus)
                .map(s -> s.equals("busy"))
                .orElse(Boolean.FALSE);
    }

    public boolean isNoAnswer() {
        return Optional.ofNullable(dialCallStatus)
                .map(s -> s.equals("no-answer"))
                .orElse(Boolean.FALSE);
    }

    public boolean isComplete() {
        return Optional.ofNullable(dialCallStatus)
                .map(s -> s.equals("completed"))
                .orElse(Boolean.FALSE);
    }

    public boolean isRefer() {
        return Optional.ofNullable(referTarget)
                .map(StringUtils::isNotBlank)
                .orElse(Boolean.FALSE);
    }
}
