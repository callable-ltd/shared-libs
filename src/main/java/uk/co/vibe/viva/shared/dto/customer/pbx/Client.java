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
public class Client extends CallCompleteHandler implements Endpoint {

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    @Min(3)
    @Max(180)
    @NotNull
    private Integer ringTime;

    @Min(60)
    @Max(14400)
    @NotNull
    private Integer maxCallTime;

    private Boolean record;

    private String present;

    private String dnisRouter;

    public DeviceDTO router(List<DeviceDTO> deviceList) {
        return deviceList.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(dnisRouter))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
