package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CallCompleteHandler {

    @NotNull
    @NotBlank
    private String onComplete;

    @NotNull
    @NotBlank
    private String onFail;

    @NotNull
    @NotBlank
    private String onNoAnswer;

    @NotNull
    @NotBlank
    private String onBusy;

    public DeviceDTO onCompleteConnect(List<DeviceDTO> deviceList) {
        return deviceList.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(onComplete))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }


    public DeviceDTO onFailConnect(List<DeviceDTO> deviceList) {
        return deviceList.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(onFail))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    public DeviceDTO onNoAnswerConnect(List<DeviceDTO> deviceList) {
        return deviceList.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(onNoAnswer))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    public DeviceDTO onBusyConnect(List<DeviceDTO> deviceList) {
        return deviceList.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(onBusy))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
