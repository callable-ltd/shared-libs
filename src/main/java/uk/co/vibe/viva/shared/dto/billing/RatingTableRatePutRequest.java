package uk.co.vibe.viva.shared.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingTableRatePutRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String caller;

    @NotNull
    @NotBlank
    private String called;

    @NotNull
    @Pattern(regexp = "^([dewa])")
    private String timeProfile;

    @NotNull
    @Min(0)
    private Integer billAfterDuration;

    @NotNull
    @Min(0)
    private Integer minDuration;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal setup;
}
