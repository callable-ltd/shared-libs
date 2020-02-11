package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrunkDTO {
    private String id;
    private String ip;
    private Integer port;
    private Integer priority;
    private String vendor;
    private Boolean monitored;
    private Boolean obp;
    private String callerFormat;
    private String calledFormat;
    private String description;
    private String zone;
    private String sid;

    public boolean isTrunk() {
        return obp == null || !obp;
    }

    public Integer getPriority(String z) {
        int zoneIncrease = z != null && z.equals(zone) ? 1000 : 1;
        return priority * zoneIncrease;
    }
}
