package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.CallbackURLDTO;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plugin extends CallCompleteHandler {

    @NotNull
    @NotBlank
    private String plugin;

    @NotNull
    @NotBlank
    private String onTransfer;

    @NotNull
    @NotBlank
    private String onError;

    public CallbackURLDTO getCallbackURL(List<CallbackURLDTO> callbacks) {
        return callbacks.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(plugin))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
