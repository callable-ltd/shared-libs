package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DdiDTO {

    private String id;

    @Pattern(regexp = "^[1-9][0-9]{10,17}",
            message = "name must be e164 valid number")
    private String name;

    @Pattern(regexp = "^(inbound|outbound)$",
            message = "direction must be one of inbound|outbound")
    private String direction;

    @Pattern(regexp = "^(full|partial)$",
            message = "match must be full|partial")
    private String match;

    private Boolean record;
    private String present;
    private Boolean mbn;

    private String trunk;
    private String ivr;
    private String device;

    public boolean hasIvr() {
        return StringUtils.isNotBlank(ivr);
    }

    public boolean hasTrunk() {
        return StringUtils.isNotBlank(trunk);
    }

    public boolean hasDevice() {
        return StringUtils.isNotBlank(device);
    }
}
