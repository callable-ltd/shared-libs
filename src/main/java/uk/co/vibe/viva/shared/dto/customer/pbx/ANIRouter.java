package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ANIRouter extends Connector {

    @NotNull
    @NotEmpty
    private List<Option> options;

    public String getConnectTo(String ani) {
        return Optional.ofNullable(options)
                .map(opts -> opts.stream()
                        .filter(option -> option.isActivePrefix(ani))
                        .max(Comparator.comparing(Option::getScore))
                        .map(Connector::getConnectTo)
                        .orElse(getConnectTo()))
                .orElse(getConnectTo());
    }

    public DeviceDTO connect(List<DeviceDTO> devices, String ani) {
        return devices.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(getConnectTo(ani)))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

}
