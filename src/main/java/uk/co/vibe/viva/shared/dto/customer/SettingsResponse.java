package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.vibe.viva.shared.dto.ApiResponse;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingsResponse extends ApiResponse<SettingsDTO> {
    public SettingsResponse() {
        super();
    }

    public SettingsResponse(SettingsDTO data) {
        super(data);
    }

    public SettingsResponse(List<SettingsDTO> items) {
        super(items);
    }

}
