package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.vibe.viva.shared.dto.ApiResponse;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CallbackResponse extends ApiResponse<CallbackURLDTO> {
    public CallbackResponse() {
        super();
    }

    public CallbackResponse(CallbackURLDTO data) {
        super(data);
    }

    public CallbackResponse(List<CallbackURLDTO> items) {
        super(items);
    }

}
