package uk.co.vibe.viva.shared.config;

import uk.co.vibe.viva.shared.dto.ApiResponse;

public class ApiResponseException extends RuntimeException {

    private final ApiResponse responseDTO;

    public ApiResponseException(ApiResponse responseDTO) {
        this.responseDTO = responseDTO;
    }

    public ApiResponse getResponse() {
        return responseDTO;
    }
}
