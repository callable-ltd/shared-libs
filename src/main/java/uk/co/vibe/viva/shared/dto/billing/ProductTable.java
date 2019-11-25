package uk.co.vibe.viva.shared.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTable {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @Valid
    @NotNull
    private List<Product> products;
}