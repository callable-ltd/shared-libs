package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneRouter extends Connector {

    @NotNull
    @NotEmpty
    private List<Option> options;

    public String getConnectTo(String zone) {
        return Optional.ofNullable(options)
                .map(opts -> opts.stream()
                        .filter(option -> option.isActive(zone))
                        .findFirst()
                        .map(Connector::getConnectTo)
                        .orElse(getConnectTo()))
                .orElse(getConnectTo());
    }

    public DeviceDTO connect(List<DeviceDTO> devices, String zone) {
        return devices.stream()
                .filter(deviceDTO -> deviceDTO.getExtension().equals(getConnectTo(zone)))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
