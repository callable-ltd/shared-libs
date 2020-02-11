package uk.co.vibe.viva.shared.dto.plugin;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uk.co.vibe.viva.shared.dto.customer.CallbackURLDTO;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.customer.DeviceDTO;
import uk.co.vibe.viva.shared.dto.ivr.IvrRequest;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class PluginFlowRequest {

    private CustomerDTO customer;
    private CallbackURLDTO callbackURL;
    private IvrRequest ivrRequest;
    private DeviceDTO device;
    private String data;
    private long timestamp;

    public PluginFlowRequest(CustomerDTO customer, CallbackURLDTO callbackURL, IvrRequest ivrRequest, DeviceDTO device) {
        this.customer = customer;
        this.callbackURL = callbackURL;
        this.ivrRequest = ivrRequest;
        this.device = device;
        this.data = device.getPlugin().getData();
        this.timestamp = System.currentTimeMillis();
    }
}
