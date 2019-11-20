package uk.co.vibe.viva.shared.dto.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrResponseVerb;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowPostRequest {

    @NotNull
    @NotEmpty
    private String to;

    private String callerId;

    @Min(5)
    @Max(180)
    private Integer timeout;

    private List<String> tags;

    @NotNull
    @NotEmpty
    private List<IvrResponseVerb> items;

    private CustomerDTO customer;

}
