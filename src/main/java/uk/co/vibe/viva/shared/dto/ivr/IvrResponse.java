package uk.co.vibe.viva.shared.dto.ivr;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import uk.co.vibe.viva.shared.dto.ApiResponse;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrRejectVerb;
import uk.co.vibe.viva.shared.dto.ivr.ivr.IvrResponseVerb;

import java.util.*;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IvrResponse extends ApiResponse<IvrResponseVerb> {

    Set<String> tags;

    public static IvrResponse decline(String reason) {
        IvrResponse response = new IvrResponse();
        response.setItems(Arrays.asList(new IvrRejectVerb("decline")));
        response.setTimestamp(System.currentTimeMillis());
        response.setStatus(HttpStatus.OK);
        response.setMessages(Arrays.asList(reason));
        return response;
    }

    public IvrResponse tag(String... tag) {
        if (tags == null)
            tags = new HashSet<>();
        tags.addAll(Arrays.asList(tag));
        return this;
    }
}
