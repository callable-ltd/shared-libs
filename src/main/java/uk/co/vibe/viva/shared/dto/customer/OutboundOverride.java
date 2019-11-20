package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.customer.pbx.Endpoint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static uk.co.vibe.viva.shared.util.CustomerUtil.getCallerId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutboundOverride implements Endpoint {

    @Min(3)
    @Max(180)
    @NotNull
    private Integer ringTime;

    @Min(60)
    @Max(14400)
    @NotNull
    private Integer maxCallTime;

    @NotNull
    private Boolean record;

    @NotNull
    @NotBlank
    private String present;

    @Min(0)
    @Max(6)
    private Integer strip;

    private Integer prepend;

    public String getPresentation(String originalFrom, CustomerDTO customer) {
        String p = present;
        if (present.equals("pass-through"))
            p = originalFrom;
        else if (present.equals("privacy"))
            p = "anonymous";
        return getCallerId(p, customer);
    }
}
