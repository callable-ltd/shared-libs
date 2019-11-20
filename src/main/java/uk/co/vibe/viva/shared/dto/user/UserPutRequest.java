package uk.co.vibe.viva.shared.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPutRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String customerId;

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

    private String token;

    public UserPutRequest(UserDTO user) {
        this.id = user.getId();
        this.customerId = user.getCustomer().getId();
        this.username = user.getUsername();
        this.apiKey = user.getApiKey();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}