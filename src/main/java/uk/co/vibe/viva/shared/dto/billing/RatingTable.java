package uk.co.vibe.viva.shared.dto.billing;

import lombok.*;
import org.springframework.data.annotation.Id;
import uk.co.vibe.viva.shared.dto.DBReference;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingTable {
    private String id;
    private String name;
    private String description;
    private DBReference codeDeck;
    private String type;
    private String appliedAt;
    private List<Rate> rates;
}
