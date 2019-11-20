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
public class NumberPlan extends Connector {

    @NotNull
    @NotEmpty
    private String text;

    @NotNull
    private Integer timeout;

    @NotNull
    @NotEmpty
    private String finishOnKey;

    @NotNull
    private Integer maxDigits;

    @NotNull
    @NotEmpty
    private List<Option> options;

    public String getConnectTo(String digits) {
        return Optional.ofNullable(options)
                .map(opts -> opts.stream()
                        .filter(option -> option.isActive(digits))
                        .findFirst()
                        .map(Connector::getConnectTo)
                        .orElse(getConnectTo()))
                .orElse(getConnectTo());
    }

    public DeviceDTO connect(List<DeviceDTO> devices, String digits) {
        return devices.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(getConnectTo(digits)))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
