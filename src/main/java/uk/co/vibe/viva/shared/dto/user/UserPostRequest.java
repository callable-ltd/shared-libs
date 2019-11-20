package uk.co.vibe.viva.shared.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPostRequest {

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

    @NotNull
    @NotEmpty
    private List<String> roles;

}