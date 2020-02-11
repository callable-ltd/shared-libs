package uk.co.vibe.viva.shared.dto.sbc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGatewayRequest {
    @NotBlank
    @NotNull
    private String sid;
    @NotBlank
    @NotNull
    private String ip;
    @NotBlank
    @NotNull
    private String port;
}
