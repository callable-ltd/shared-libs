package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddressPutRequest {
    @NotNull
    @NotEmpty
    private String customerId;
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
