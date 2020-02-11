package uk.co.vibe.viva.shared.dto.sbc;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DeleteGatewayRequest {
    @NotBlank
    @NotNull
    private String sid;
}
