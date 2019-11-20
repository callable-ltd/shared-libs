package uk.co.vibe.viva.shared.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPutRolesRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String customerId;

    @NotNull
    @NotEmpty
    private Set<String> roles;


}