package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uk.co.vibe.viva.shared.dto.customer.pbx.*;

import java.util.Optional;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceDTO {

    public static final String SIP = "SIP";
    public static final String SIP_GATEWAY = "SIP_GATEWAY";
    public static final String CLIENT = "CLIENT";
    public static final String REMOTE = "REMOTE";
    public static final String ROUTE_START = "ROUTE_START";
    public static final String OUTBOUND_OVERRIDE = "OUTBOUND_OVERRIDE";
    public static final String HUNT_GROUP = "HUNT_GROUP";
    public static final String TIME_ROUTER = "TIME_ROUTER";
    public static final String ANI_ROUTER = "ANI_ROUTER";
    public static final String DNIS_ROUTER = "DNIS_ROUTER";
    public static final String NUMBER_PLAN = "NUMBER_PLAN";
    public static final String ZONE_ROUTER = "ZONE_ROUTER";
    public static final String PLUGIN = "PLUGIN";
    public static final String SAY = "SAY";
    public static final String PLAY = "PLAY";
    public static final String VOICEMAIL = "VOICEMAIL";

    private String id;
    private String type;
    private String name;
    private String extension;
    private Integer priority;

    private OutboundOverride outboundOverride;
    private TimeBasedRoute timeBasedRoute;
    private ANIRouter aniRouter;
    private DNISRouter dnisRouter;
    private StartRoute startRoute;
    private NumberPlan numberPlan;
    private ZoneRouter zoneRouter;
    private HuntGroup huntGroup;
    private Client client;
    private SipExtension sipExtension;
    private SipGateway sipGateway;
    private Remote remote;
    private Plugin plugin;
    private Play play;
    private Say say;
    private VoiceMail voicemail;



    public Integer getPriority() {
        return Optional.ofNullable(priority)
                .orElse(0);
    }

    public String getClientUsername() {
        return Optional.ofNullable(client)
                .map(Client::getUsername)
                .orElse(null);
    }

    public boolean containsDDI(DdiDTO ddi) {
       return startRoute.getDdis().contains(ddi.getId());
    }
}
