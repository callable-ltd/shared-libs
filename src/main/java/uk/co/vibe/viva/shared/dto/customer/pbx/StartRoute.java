package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class StartRoute extends Connector {
    @NotNull
    @NotEmpty
    private List<String> ddis;
}
