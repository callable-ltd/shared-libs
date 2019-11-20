package uk.co.vibe.viva.shared.dto.billing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDeckCodePostRequest {

    private String id;

    @NotNull
    @NotBlank
    private String caller;
    @NotNull
    @NotBlank
    private String called;
    @NotNull
    @NotBlank
    private String code;
    @NotNull
    @NotBlank
    private String category;
    @NotNull
    @NotBlank
    private String country;
    @NotNull
    @NotBlank
    private String countryCode;

}
