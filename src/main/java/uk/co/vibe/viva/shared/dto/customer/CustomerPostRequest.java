package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPostRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String mbn;

    @NotNull
    @NotBlank
    private String clientID;

    @NotNull
    @NotBlank
    private String domain;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @Valid
    private AddressDTO address;

}