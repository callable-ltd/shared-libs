package uk.co.vibe.viva.shared.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsPutRequest {
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String key;
    @NotNull
    @NotBlank
    private String type;
    @NotNull
    @NotBlank
    private String value;
}
