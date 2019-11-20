package uk.co.vibe.viva.shared.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenRequest {

    @NotBlank
    @NotNull
    private String id;

    @NotBlank
    @NotNull
    private String token;

    public UserTokenRequest(UserDTO user, String token) {
        this.id = user.getId();
        this.token = token;
    }
}
