package uk.co.vibe.viva.shared.services.rest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import uk.co.vibe.viva.shared.dto.callmanager.RcResponse;

import java.nio.charset.StandardCharsets;

public class CallmanagerRestService {

    private static String urlPlaceholder = "https://[ZONE].vibecoms.net/restcomm/2012-04-24/Accounts/[ACCOUNT]/Calls.json";

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    private final String rcZone;
    private final String rcAccount;
    private final String rcToken;

    public CallmanagerRestService(String rcZone, String rcAccount, String rcToken) {
        this.rcZone = rcZone;
        this.rcAccount = rcAccount;
        this.rcToken = rcToken;
    }

    public RcResponse post(MultiValueMap<String, String> map) {
        String url = getCallsURL();
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, defaultHeaders());
        return restTemplate.exchange(url, HttpMethod.POST, entity, RcResponse.class).getBody();
    }

    public RcResponse put(MultiValueMap<String, String> map, String pid) {
        String url = getCallsURL()+ "/" + pid;
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, defaultHeaders());
        return restTemplate.exchange(url, HttpMethod.POST, entity, RcResponse.class).getBody();
    }

    public RcResponse get(String pid) {
        String url = getCallsURL() + "/" + pid;
        HttpEntity entity = new HttpEntity<>(defaultHeaders());
        return restTemplate.exchange(url, HttpMethod.GET, entity, RcResponse.class).getBody();
    }

    private String getCallsURL() {
        return urlPlaceholder.replace("[ZONE]", rcZone).replace("[ACCOUNT]", rcAccount);
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", getBasicAuthHeader(rcAccount, rcToken));
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("Accept", "application/json");
        return headers;
    }

    private String getBasicAuthHeader(String key, String token) {
        String auth = key + ":" + token;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(encodedAuth);
    }


}
