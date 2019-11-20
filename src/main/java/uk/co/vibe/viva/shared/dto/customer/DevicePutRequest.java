package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.customer.pbx.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DevicePutRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String extension;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    @NotBlank
    private String name;

    @Valid
    private CallCompleteHandler callCompleteHandler;
    @Valid
    private TimeBasedRoute timeBasedRoute;
    @Valid
    private ANIRouter aniRouter;
    @Valid
    private DNISRouter dnisRouter;
    @Valid
    private StartRoute startRoute;
    @Valid
    private HuntGroup huntGroup;
    @Valid
    private NumberPlan numberPlan;
    @Valid
    private ZoneRouter zoneRouter;
    @Valid
    private Client client;
    @Valid
    private SipExtension sipExtension;
    @Valid
    private SipGateway sipGateway;
    @Valid
    private Remote remote;
    @Valid
    private Plugin plugin;
    @Valid
    private Play play;
    @Valid
    private Say say;
    @Valid
    private VoiceMail voicemail;
    @Valid
    private OutboundOverride outboundOverride;
}
