package uk.co.vibe.viva.shared.util;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import uk.co.vibe.viva.shared.dto.customer.CallbackURLDTO;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CallbackAuthorisation {
    public static HttpHeaders getAuthHeaders(CallbackURLDTO callback) {
        return new HttpHeaders() {{
            switch (callback.getAuthType()) {
                case "KEY":
                    set(callback.getApiKeyName(), callback.getApiKeyValue());
                    break;
                case "KEY_SECRET":
                    set(callback.getApiKeyName(), callback.getApiKeyValue());
                    set(callback.getApiSecretName(), callback.getApiSecretValue());
                    break;
                case "BASIC_AUTH":
                    String auth = callback.getApiSecretName() + ":" + callback.getApiSecretValue();
                    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
                    String authHeader = "Basic " + new String(encodedAuth);
                    set("Authorization", authHeader);
                    break;
            }
        }};
    }
}
