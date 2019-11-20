package uk.co.vibe.viva.shared.dto.customer.pbx;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeBasedRoute extends Connector {

    @Valid
    @NotNull
    private List<TimeOfDay> timeOfDayRoutes;


    public String getConnectTo(LocalDateTime nowDate) {
        return timeOfDayRoutes.stream()
                .filter(timeOfDay -> timeOfDay.isActive(nowDate))
                .max(Comparator.comparing(TimeOfDay::getScore))
                .map(Connector::getConnectTo)
                .orElse(getConnectTo());
    }

    public DeviceDTO connect(List<DeviceDTO> devices, String c) {
        return devices.stream()
                .filter(deviceDTO -> deviceDTO.getId().equals(c))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
