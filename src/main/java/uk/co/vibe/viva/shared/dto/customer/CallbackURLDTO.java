package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallbackURLDTO {
    private String id;
    private String name;
    private String app;
    private String url;
    private String authType;
    private String apiKeyName;
    private String apiKeyValue;
    private String apiSecretName;
    private String apiSecretValue;
    private String endpoint;
    private String method;
    private Integer priority;
    public boolean isApplication() {
        return app.contains("ivr");
    }
}
