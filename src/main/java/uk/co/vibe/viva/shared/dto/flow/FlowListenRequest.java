package uk.co.vibe.viva.shared.dto.flow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlowListenRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String status;

    @NotNull
    private CustomerDTO customer;


}
