package uk.co.vibe.viva.shared.dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatRequest {

    @NotNull
    private String stat;

    private Set<String> customerIds;

    private String filters;

    private String type = "terms";
    private String keyword = "customerName.keyword";
    private String aggField = "seconds";
    private String aggType = "count";
    private String duration = "PT24H";
    private String offset = "PT0S";
    private Integer bucketSize = 10;
}
