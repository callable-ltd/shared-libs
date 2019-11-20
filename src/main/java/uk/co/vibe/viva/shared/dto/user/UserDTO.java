package uk.co.vibe.viva.shared.dto.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class UserDTO {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String apiKey;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotEmpty
    private List<String> roles;

    @NotNull
    @NotBlank
    private UserCustomerDTO customer;

    @NotNull
    private List<UserCustomerDTO> customers;

    private String token;

    public List<UserCustomerDTO> getCustomers() {
        return Optional.ofNullable(customers)
                .orElse(new ArrayList<>());
    }

    public List<String> getRoles() {
        return Optional.ofNullable(roles)
                .orElse(new ArrayList<>());
    }

}
