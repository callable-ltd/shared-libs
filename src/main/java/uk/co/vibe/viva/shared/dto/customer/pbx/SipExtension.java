package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SipExtension extends CallCompleteHandler implements Endpoint {


    @Min(3)
    @Max(180)
    @NotNull
    private Integer ringTime;

    @Min(60)
    @Max(14400)
    @NotNull
    private Integer maxCallTime;

    private Boolean record;

    @NotNull
    @NotBlank
    private String gateway;

    private String present;

    public SipGateway getGateway(List<DeviceDTO> devices) {
        return devices.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(gateway))
                .findFirst()
                .map(DeviceDTO::getSipGateway)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
