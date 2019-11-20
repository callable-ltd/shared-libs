package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.Data;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Connector {

    @NotNull
    @NotBlank
    private String connectTo;

    public DeviceDTO connect(List<DeviceDTO> devices) {
        return devices.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(connectTo))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    public Boolean isHangup() {
        return connectTo.toLowerCase().equals("hangup");
    }

}
