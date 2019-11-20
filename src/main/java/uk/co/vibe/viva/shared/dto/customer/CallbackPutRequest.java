package uk.co.vibe.viva.shared.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CallbackPutRequest {

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
