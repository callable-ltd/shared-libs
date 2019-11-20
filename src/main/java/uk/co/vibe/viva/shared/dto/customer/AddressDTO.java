package uk.co.vibe.viva.shared.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressDTO {
    @NotNull
    @NotEmpty
    private String line1;
    @NotNull
    @NotEmpty
    private String line2;
    @NotNull
    @NotEmpty
    private String level1;
    @NotNull
    @NotEmpty
    private String level2;
    @NotNull
    @NotEmpty
    private String postalCode;
    @NotNull
    @NotEmpty
    private String country;
}
