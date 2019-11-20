package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String mbn;
    @NotNull
    @NotBlank
    private String clientID;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    private List<DdiDTO> ddis;
    @NotNull
    private List<TrunkDTO> trunks;
    @NotNull
    private List<CallbackURLDTO> callbackURLS;
    @NotNull
    private List<DeviceDTO> devices;
    @NotNull
    private List<SettingsDTO> settings;

    private AddressDTO address;

    public List<DdiDTO> getDdis() {
        return Optional.ofNullable(ddis)
                .orElse(new ArrayList<>());
    }

    public List<DeviceDTO> getDevices() {
        return Optional.ofNullable(devices)
                .orElse(new ArrayList<>());
    }

    public List<TrunkDTO> getTrunks() {
        return Optional.ofNullable(trunks)
                .orElse(new ArrayList<>());
    }

    public List<CallbackURLDTO> getCallbackURLS() {
        return Optional.ofNullable(callbackURLS)
                .orElse(new ArrayList<>());
    }

    public List<SettingsDTO> getSettings() {
        return Optional.ofNullable(settings)
                .orElse(new ArrayList<>());
    }

    public Optional<DdiDTO> getDDI(String name) {
        return getDdis().stream()
                .filter(ddiDTO -> ddiDTO.getName().equals(name))
                .findFirst();
    }
}