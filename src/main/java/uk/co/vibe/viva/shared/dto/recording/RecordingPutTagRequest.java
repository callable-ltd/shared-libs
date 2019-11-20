package uk.co.vibe.viva.shared.dto.recording;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordingPutTagRequest {
    @NotNull
    @NotBlank
    private String id;
    @NotNull
    @NotBlank
    private String tag;
}
