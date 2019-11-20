package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DDIPutRequest {

    @NotNull
    @NotBlank
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

}
