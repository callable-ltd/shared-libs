package uk.co.vibe.viva.shared.dto.pbx;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.events.MonitoredTrunkAttachRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PbxEventDTO {
    @NotNull
    @NotBlank
    private String customerId;
    @NotNull
    @NotBlank
    private String ip;
    @NotNull
    @NotBlank
    private String vendor;
    @NotNull
    @NotBlank
    private String callerFormat;
    @NotNull
    @NotBlank
    private String calledFormat;
    @NotNull
    @NotBlank
    private String customerKey;
    @NotNull
    @NotBlank
    private String customerMBN;
    @NotNull
    @NotBlank
    private String zone;
    @NotNull
    @NotBlank
    private String eventType;
    @NotNull
    private JsonNode data;

    private CustomerDTO customer;
}
