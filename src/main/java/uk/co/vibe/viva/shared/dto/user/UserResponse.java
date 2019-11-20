package uk.co.vibe.viva.shared.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.vibe.viva.shared.dto.ApiResponse;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse extends ApiResponse<UserDTO> {

}
