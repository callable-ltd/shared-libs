package uk.co.vibe.viva.shared.dto.stat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleXYWrapper {
    private List<SimpleXY> data;
}
