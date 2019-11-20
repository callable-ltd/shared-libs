package uk.co.vibe.viva.shared.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseBuilder {

    private HttpStatus status;

    @Singular
    private List<String> messages;

    @Singular
    private List<String> errors;

    @Singular
    private List<String> warnings;

    private long timestamp;

    private Paged paged;
}
