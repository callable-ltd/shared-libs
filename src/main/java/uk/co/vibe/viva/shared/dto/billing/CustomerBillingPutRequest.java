package uk.co.vibe.viva.shared.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.DBReference;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerBillingPutRequest {
    @NotNull
    @NotBlank
    private String customerId;
    @NotNull
    @NotBlank
    private String customerType;
    @NotNull
    @NotBlank
    private String customerName;
    @NotNull
    private String owner;
    @NotNull
    private String platformRates;
    @NotNull
    private String originationRates;
    @NotNull
    private String terminationRates;

    @Valid
    @NotNull
    private CustomerPlatformBundle customerPlatformBundle;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CustomerPlatformBundle {

        @NotNull
        private long asr;
        @NotNull
        private long tts;
        @NotNull
        private long recording;
        @NotNull
        private long compute;
        @NotNull
        private long inbound;
        @NotNull
        private long outbound;

        @Valid
        @NotNull
        private String inboundCodeDeck;

        @Valid
        @NotNull
        private String outboundCodeDeck;
    }
}
