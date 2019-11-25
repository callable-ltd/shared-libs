package uk.co.vibe.viva.shared.dto.ivr.ivr;

import org.apache.commons.lang3.StringUtils;

public class IvrHelper {

    public static String getTypeFromDestination(String destination) {
        if (StringUtils.isBlank(destination))
            return IvrConstants.TRUNK;
        if (isSIPEndpoint(destination))
            return IvrConstants.SIP;
        if (isText(destination))
            return IvrConstants.TRUNK;
        if (isClient(destination))
            return IvrConstants.CLIENT;
        if (isEmergency(destination))
            return IvrConstants.PSTN;
        if (isDQ(destination))
            return IvrConstants.PSTN;
        if (isShortDial(destination))
            return IvrConstants.TRUNK;
        return IvrConstants.DIAL;
    }

    private static boolean isText(String destination) {
        return destination.matches("^[a-zA-Z]*$");
    }

    private static boolean isSIPEndpoint(String destination) {
        return destination.startsWith("sip:") || destination.contains("@");
    }

    private static boolean isClient(String destination) {
        return destination.startsWith("client");
    }

    private static boolean isShortDial(String destination) {
        return destination.length() <= 8;
    }

    private static boolean isDQ(String destination) {
        return destination.startsWith("118") && destination.length() == 6;
    }

    private static boolean isEmergency(String destination) {
        return destination.startsWith("999");
    }

}
