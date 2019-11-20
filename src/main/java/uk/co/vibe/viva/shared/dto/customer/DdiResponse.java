package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.vibe.viva.shared.dto.ApiResponse;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DdiResponse extends ApiResponse<DdiDTO> {
    public DdiResponse() {
        super();
    }

    public DdiResponse(DdiDTO data) {
        super(data);
    }

    public DdiResponse(List<DdiDTO> items) {
        super(items);
    }

}
