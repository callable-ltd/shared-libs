package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.customer.pbx.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDevicePostRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String extension;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @Valid
    private Client client;

}
