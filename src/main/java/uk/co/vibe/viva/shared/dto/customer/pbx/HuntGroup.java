package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HuntGroup extends CallCompleteHandler implements Endpoint {

    @Min(3)
    @Max(180)
    private Integer ringTime;

    @Min(60)
    @Max(14400)
    private Integer maxCallTime;

    @NotNull
    private Boolean record;

    private String present;

    @NotNull
    @NotEmpty
    private List<String> devices;


}
