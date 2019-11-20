package uk.co.vibe.viva.shared.dto.customer.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option extends Connector {

    private String option;

    public Long getScore() {
        return (long) option.length();
    }

    public Boolean isActive(String str) {
        return str.equals(option);
    }

    public Boolean isActivePrefix(String str) {
        return str.startsWith(option);
    }
}
