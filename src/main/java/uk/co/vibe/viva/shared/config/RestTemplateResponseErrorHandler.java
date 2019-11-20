package uk.co.vibe.viva.shared.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;
import uk.co.vibe.viva.shared.dto.ApiResponse;

import javax.imageio.stream.ImageOutputStreamImpl;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return (
                clientHttpResponse.getStatusCode().series() == CLIENT_ERROR
                        || clientHttpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        log.error("clientError {}", clientHttpResponse);
        if (clientHttpResponse.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            log.error("Server error on rest client {},{},{}",
                    clientHttpResponse.getStatusText(),
                    clientHttpResponse.getRawStatusCode(),
                    clientHttpResponse.getBody().toString());
            throw new RuntimeException("Internal Error");
        } else if (clientHttpResponse.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("Client error - Not Found");
                throw new ResourceNotFoundException();
            } else if (clientHttpResponse.getStatusCode() == HttpStatus.BAD_REQUEST) {
                ObjectInputStream ois = new ObjectInputStream(clientHttpResponse.getBody());
                try {
                    ApiResponse responseDTO = (ApiResponse) ois.readObject();
                    log.error("Client error - Bad Request {}", responseDTO);
                    throw new ApiResponseException(responseDTO);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                String body = StreamUtils.copyToString(clientHttpResponse.getBody(), Charset.defaultCharset());
                log.error("Client error - status={}, headers={}, body={}",
                        clientHttpResponse.getRawStatusCode(),
                        clientHttpResponse.getHeaders().toSingleValueMap(),
                        body);
                throw new ApiResponseException(new ApiResponse().error(body));
            }
        }
    }
}
