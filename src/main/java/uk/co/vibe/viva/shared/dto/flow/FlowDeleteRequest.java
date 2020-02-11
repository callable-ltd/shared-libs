package uk.co.vibe.viva.shared.dto.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrResponseVerb;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowDeleteRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    private CustomerDTO customer;


}
