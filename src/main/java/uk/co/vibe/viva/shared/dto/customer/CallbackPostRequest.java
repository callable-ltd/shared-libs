package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallbackPostRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String app;
    @NotNull
    @NotBlank
    private String url;

    @NotNull
    @NotBlank
    private String method;

    @NotNull
    private Integer priority;

    private String authType;
    private String apiKeyName;
    private String apiKeyValue;
    private String apiSecretName;
    private String apiSecretValue;
    private String endpoint;
}
