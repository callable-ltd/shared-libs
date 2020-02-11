package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrunkPostRequest {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String callerFormat;

    @NotNull
    @NotBlank
    private String calledFormat;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String ip;

    @NotNull
    private Boolean monitored;

    @NotNull
    private Integer port;

    @NotNull
    private Integer priority;

    @NotNull
    @NotBlank
    private String vendor;

    @NotNull
    @NotBlank
    private String zone;

    private String sid;
}
