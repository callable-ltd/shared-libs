package uk.co.vibe.viva.shared.dto.callmanager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.ApiResponse;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrResponseVerb;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ConnectRequest {

    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String callerId;
    @NotNull
    @NotBlank
    private String to;
    @NotNull
    @NotBlank
    private Long timeout;
    @NotNull
    private List<IvrResponseVerb> response;

    public ConnectRequest(String callerId, String to, Long timeout, IvrResponseVerb... response) {
        this.id = UUID.randomUUID().toString();
        this.to = to;
        this.timeout = timeout;
        this.response = Arrays.asList(response);
    }


}
