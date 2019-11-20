package uk.co.vibe.viva.shared.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.DBReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBilling {
    private String id;
    private String customerId;
    private String customerType;
    private String customerName;
    private DBReference owner;
    private DBReference platformRates;
    private DBReference originationRates;
    private DBReference terminationRates;
    private CustomerPlatformBundle customerPlatformBundle;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomerPlatformBundle {
        private long asr;
        private long tts;
        private long recording;
        private long compute;
        private long inbound;
        private long outbound;
        private DBReference inboundCodeDeck;
        private DBReference outboundCodeDeck;
    }
}
