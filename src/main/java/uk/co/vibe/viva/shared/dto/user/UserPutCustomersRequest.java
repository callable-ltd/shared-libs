package uk.co.vibe.viva.shared.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPutCustomersRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String customerId;

    @NotNull
    private Set<String> customers;


}