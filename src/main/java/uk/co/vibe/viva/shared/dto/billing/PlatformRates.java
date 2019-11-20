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
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformRates {
    private String id;
    private String name;
    private BigDecimal compute;
    private BigDecimal asr;
    private BigDecimal tts;
    private BigDecimal recording;
}
