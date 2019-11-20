package uk.co.vibe.viva.shared.dto.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uk.co.vibe.viva.shared.dto.customer.CallbackURLDTO;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class ForwardingEvent {

    private CustomerDTO customer;
    private CallbackURLDTO callbackURL;
    private DialEvent dialEvent;
    private long timestamp;

    public ForwardingEvent(CustomerDTO customer, CallbackURLDTO callbackURL, DialEvent dialEvent) {
        this.customer = customer;
        this.callbackURL = callbackURL;
        this.dialEvent = dialEvent;
        this.timestamp = System.currentTimeMillis();
    }
}
