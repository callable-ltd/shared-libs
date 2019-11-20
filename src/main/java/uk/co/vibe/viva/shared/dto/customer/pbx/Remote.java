package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Remote extends CallCompleteHandler implements Endpoint {

    @NotNull
    @NotBlank
    private String destination;

    @Min(3)
    @Max(180)
    @NotNull
    private Integer ringTime;

    @Min(60)
    @Max(14400)
    @NotNull
    private Integer maxCallTime;

    private Boolean record;

    private String present;
}
