package uk.co.vibe.viva.shared.dto.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitoredTrunkAttachRequest {
    @NotNull
    @NotBlank
    private String ip;
    @NotNull
    @NotBlank
    private String customerId;
    @NotNull
    @NotBlank
    private String caller;
    @NotNull
    @NotBlank
    private String called;
    @NotNull
    @NotBlank
    private String pbxId;
    @NotNull
    private Long eventTime;
}
