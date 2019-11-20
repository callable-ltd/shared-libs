package uk.co.vibe.viva.shared.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uk.co.vibe.viva.shared.dto.ApiResponse;

import javax.annotation.PostConstruct;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @PostConstruct
    public void init() {
        log.info("init() Global Rest Exception Handler");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        log.error("Validation Error {}", ex.getBindingResult());
        return handleExceptionInternal(
                ex,
                ApiResponse.badRequest("Validation Error", ex.getBindingResult()),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Validation Error {}", ex.getBindingResult());
        return handleExceptionInternal(
                ex,
                ApiResponse.badRequest("Validation Error", ex.getBindingResult()),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Missing parameter {}", ex.getParameterName());
        return new ResponseEntity<>(
                ApiResponse.badRequest("Missing parameter", ex.getParameterName() + " parameter is missing"),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotUniqueException.class})
    public ResponseEntity<Object> handleNotUnique(NotUniqueException ex, WebRequest request) {
        log.error("Not Unique {}", ex.getMessage());
        return new ResponseEntity<>(
                ApiResponse.badRequest("Not Unique", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(ResourceNotFoundException ex) {
        log.error("handleMethodArgumentTypeMismatch {}", ex.getMessage());
        return new ResponseEntity<>(
                new ApiResponse<>().notFound(),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }
}
