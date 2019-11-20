package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;
import uk.co.vibe.viva.shared.dto.customer.TrunkDTO;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SipGateway extends CallCompleteHandler implements Endpoint {


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
    @NotEmpty
    private List<String> trunks;

    private String present;

    public TrunkDTO trunk(List<TrunkDTO> trunkList, String zone) {
        return trunks.stream()
                .map(s -> getTrunkDTO(trunkList, s))
                .filter(trunkDTO -> zone.equals(trunkDTO.getZone()))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    private TrunkDTO getTrunkDTO(List<TrunkDTO> trunkList, String s) {
        return trunkList.stream().filter(trunkDTO -> trunkDTO.getId().equals(s)).findFirst().orElseThrow(ResourceNotFoundException::new);
    }
}
