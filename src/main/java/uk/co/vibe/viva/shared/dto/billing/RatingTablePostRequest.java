package uk.co.vibe.viva.shared.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingTablePostRequest {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String codeDeck;

    @NotBlank
    @Pattern(regexp = "^(customer|vendor)")
    private String type;

    @NotBlank
    @Pattern(regexp = "^(origination|termination)")
    private String appliedAt;


}
