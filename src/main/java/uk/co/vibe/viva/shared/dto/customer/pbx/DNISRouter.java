package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DNISRouter extends Connector {

    @NotNull
    @NotEmpty
    private List<Option> options;

    public String getConnectTo(String dnis) {
        return options.stream()
                .filter(option -> {
                    boolean match = option.isActivePrefix(dnis);
                    log.info("Checking dnis against option {},{}, isTrue={}", option, dnis, match);
                    return match;
                })
                .max(Comparator.comparing(Option::getScore))
                .map(option -> {
                    log.info("Mapping dnis option {}", option);
                    return option.getConnectTo();
                })
                .orElseGet(this::getConnectTo);
    }

    public DeviceDTO connect(List<DeviceDTO> devices, String dnis) {
        String c = getConnectTo(dnis);
        log.info("return connect {}", c);
        return devices.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(c))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
