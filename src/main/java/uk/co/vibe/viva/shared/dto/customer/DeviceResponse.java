package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.vibe.viva.shared.dto.ApiResponse;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceResponse extends ApiResponse<DeviceDTO> {
    public DeviceResponse() {
        super();
    }

    public DeviceResponse(DeviceDTO data) {
        super(data);
    }

    public DeviceResponse(List<DeviceDTO> items) {
        super(items);
    }

}
