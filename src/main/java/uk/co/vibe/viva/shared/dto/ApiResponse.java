package uk.co.vibe.viva.shared.dto;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private HttpStatus status;
    private List<String> messages;
    private List<String> errors;
    private List<String> warnings;
    private long timestamp;
    private Paged paged;

    private List<T> items;

    private T data;

    public ApiResponse(T data) {
        this.status = HttpStatus.OK;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public ApiResponse(List<T> items) {
        list(items);
    }

    public static ApiResponse badRequest(String message, BindingResult result) {
        ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessages(Collections.singletonList(message));
        response.setTimestamp(System.currentTimeMillis());
        for (FieldError error : result.getFieldErrors()) {
            response.error(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : result.getGlobalErrors()) {
            response.error(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        return response;
    }

    public ApiResponse<T> notFound() {
        this.status = HttpStatus.NOT_FOUND;
        this.timestamp = System.currentTimeMillis();
        this.messages = Collections.singletonList("could not find resource");
        return this;
    }

    public ApiResponse<T> notFound(String id) {
        this.status = HttpStatus.NOT_FOUND;
        this.timestamp = System.currentTimeMillis();
        this.messages = Collections.singletonList("resource=" + this.getClass().getSimpleName() + ", id=" + id + " not found");
        return this;
    }

    public static ApiResponse badRequest(String message, String... error) {
        ApiResponse response = new ApiResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setMessages(Collections.singletonList(message));
        response.setTimestamp(System.currentTimeMillis());
        response.error(error);
        return response;
    }

    public ApiResponse error(String... error) {
        if (errors == null)
            errors = new ArrayList<>();
        errors.addAll(Arrays.asList(error));
        return this;
    }

    public ApiResponse warning(String... warning) {
        if (warnings == null)
            warnings = new ArrayList<>();
        warnings.addAll(Arrays.asList(warning));
        return this;
    }

    public ApiResponse<T> page(List<T> content, Paged paged) {
        this.status = HttpStatus.OK;
        this.items = content;
        this.paged = paged;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public ApiResponse<T> single(T item) {
        this.status = HttpStatus.OK;
        this.items = Collections.singletonList(item);
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public ApiResponse<T> list(List<T> items) {
        this.status = HttpStatus.OK;
        this.items = items;
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public ApiResponse<T> item(T... items) {
        this.status = HttpStatus.OK;
        if (this.items == null)
            this.items = new ArrayList<>();
        this.items.addAll(Arrays.asList(items));
        this.timestamp = System.currentTimeMillis();
        return this;
    }

    public Optional<T> first() {
        return CollectionUtils.isEmpty(items) ?
                Optional.empty() : Optional.ofNullable(items.get(0));
    }


}
