package uk.co.vibe.viva.shared.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformRatesPutRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private BigDecimal compute;

    @NotNull
    @Min(0)
    private BigDecimal asr;

    @NotNull
    @Min(0)
    private BigDecimal tts;

    @NotNull
    @Min(0)
    private BigDecimal recording;

}
