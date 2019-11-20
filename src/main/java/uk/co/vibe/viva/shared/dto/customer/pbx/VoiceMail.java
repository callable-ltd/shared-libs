package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoiceMail extends Connector {

    @NotNull
    @NotBlank
    private String text;

    @NotNull
    @NotBlank
    private String finishOnKey;


    @NotNull
    @NotBlank
    private List<String> emailRecipients;

    @Min(5)
    @Max(20)
    private Integer timeout;

    @Min(5)
    @Max(180)
    private Integer maxLength;

    @NotNull
    private Boolean transcribe;

    @NotNull
    private Boolean playBeep;
}
