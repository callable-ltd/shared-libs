package uk.co.vibe.viva.shared.services.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import uk.co.vibe.viva.shared.dto.CreatedResponse;

import java.net.URI;
import java.util.Map;

@Slf4j
@Service
public class VivaRestService {

    @Autowired
    @Qualifier("internalRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("internalLBRestTemplate")
    private RestTemplate lbRestTemplate;

    public <T> T get(String url, String id, Map<String, String> projections, Class<T> clazz) {
        log.info("get <url={}, id={}, projections={}, class={}>", url, id, projections, clazz);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/" + id);
        if (projections != null)
            projections.forEach(builder::queryParam);
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        return rt(url).exchange(builder.toUriString(), HttpMethod.GET, httpEntity, clazz)
                .getBody();
    }

    public <T> T get(String url, Map<String, String> projections, Class<T> clazz) {
        log.info("get <url={}, projections={}, class={}>", url, projections, clazz);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (projections != null)
            projections.forEach(builder::queryParam);
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        return rt(url).exchange(builder.toUriString(), HttpMethod.GET, httpEntity, clazz)
                .getBody();
    }

    public <T> T find(String url, Map<String, String> query, Class<T> clazz) {
        log.info("find <url={}, projections={}, class={}>", url, query, clazz);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        query.forEach(builder::queryParam);
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        return rt(url).exchange(builder.toUriString(), HttpMethod.GET, httpEntity, clazz)
                .getBody();
    }

    public <T> void put(Object obj, String url, Class<T> requestClass) {
        log.info("put <url={}, object={}, class={}>", url, obj, requestClass);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<T> httpEntity = new HttpEntity<>((T) obj, new HttpHeaders());
        rt(url).put(builder.toUriString(), httpEntity);
    }

    public <T> URI postForLocation(Object obj, String url, Class<T> requestClazz) {
        log.info("postForLocation <url={}, object={}, class={}>", url, obj, requestClazz);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<T> httpEntity = new HttpEntity<>((T) obj, new HttpHeaders());
        return rt(url).postForLocation(builder.toUriString(), httpEntity);
    }

    public <T, U> U postForEntity(Object obj, String url, Class<T> requestClass, Class<U> responseClass) {
        log.info("postForEntity <url={}, object={}, class={}>", url, obj, requestClass);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<T> httpEntity = new HttpEntity<>((T) obj, new HttpHeaders());
        return rt(url).postForEntity(builder.toUriString(), httpEntity, responseClass)
                .getBody();
    }

    public void delete(String url, String id) {
        log.info("delete <url={}, id={}>", url, id);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url + "/" + id);
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        rt(url).exchange(builder.toUriString(), HttpMethod.DELETE, httpEntity, String.class);
    }

    public void delete(String path) {
        log.info("delete <url={}>", path);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path);
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        rt(path).exchange(builder.toUriString(), HttpMethod.DELETE, httpEntity, String.class);
    }

    private RestTemplate rt(String url) {
        return url.matches("(https?://.*):(\\d*)\\/?(.*)") ? restTemplate : lbRestTemplate;
    }

    public <T> CreatedResponse postForEntity(Object obj, String url, Class<T> requestClass) {
        log.info("post <url={}, object={}, class={}>", url, obj, requestClass);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpEntity<T> httpEntity = new HttpEntity<T>((T) obj, new HttpHeaders());
        return rt(url)
                .postForEntity(builder.toUriString(), httpEntity, CreatedResponse.class)
                .getBody();
    }
}
