package uk.co.vibe.viva.shared.dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleXY {
    private String name;
    private String x;
    private Number y;
}
