package uk.co.vibe.viva.shared.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${apikey.header}")
    private String principalRequestHeader;

    @Value("${apikey.value}")
    private String principalRequestValue;

    //    @Bean
//    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
//        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager();
//        result.setMaxTotal(200);
//        result.setDefaultMaxPerRoute(100);
//        return result;
//    }
//
//    @Bean
//    public RequestConfig config() {
//        return RequestConfig.custom()
//                .setConnectionRequestTimeout(15000)
//                .setConnectTimeout(15000)
//                .setSocketTimeout(15000)
//                .build();
//    }
//
//    @Bean
//    public CloseableHttpClient client() {
//        return HttpClientBuilder
//                .create()
//                .setConnectionManager(poolingHttpClientConnectionManager())
//                .setDefaultRequestConfig(config())
//                .setMaxConnPerRoute(100)
//                .setMaxConnTotal(200)
//                .build();
//    }
//
//    @Bean
//    public RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setHttpClient(client());
//        return new RestTemplate(requestFactory);
//    }

    @Bean
    @Primary
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate internalRestTemplate() {
        RestTemplateBuilder restTemplate = new RestTemplateBuilder()
                .errorHandler(new RestTemplateResponseErrorHandler())
                .interceptors((request, body, execution) -> {
                    request.getHeaders().set(principalRequestHeader, principalRequestValue);
                    if (!request.getHeaders().containsKey("Content-Type"))
                        request.getHeaders().set("Content-Type", "application/json");
                    if (!request.getHeaders().containsKey("Accept"))
                        request.getHeaders().set("Accept", "application/json");
                    return execution.execute(request, body);
                });
        return restTemplate.build();
    }

    @Bean
    @LoadBalanced
    public RestTemplate internalLBRestTemplate() {
        RestTemplateBuilder restTemplate = new RestTemplateBuilder()
                .errorHandler(new RestTemplateResponseErrorHandler())
                .interceptors((request, body, execution) -> {
                    request.getHeaders().set(principalRequestHeader, principalRequestValue);
                    if (!request.getHeaders().containsKey("Content-Type"))
                        request.getHeaders().set("Content-Type", "application/json");
                    if (!request.getHeaders().containsKey("Accept"))
                        request.getHeaders().set("Accept", "application/json");
                    return execution.execute(request, body);
                });
        return restTemplate.build();
    }


}
