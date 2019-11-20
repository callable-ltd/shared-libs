package uk.co.vibe.viva.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatedResponse extends ApiResponse<Created> {

    public CreatedResponse() {
        super();
    }

    public CreatedResponse(Created data) {
        super(data);
    }


}
