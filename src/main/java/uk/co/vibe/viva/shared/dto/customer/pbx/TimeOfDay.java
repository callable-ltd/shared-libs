package uk.co.vibe.viva.shared.dto.customer.pbx;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeOfDay extends Connector {

    @NotNull
    @NotBlank
    private String id;

    @NotNull
    private LocalDateTime dateFrom;

    @NotNull
    private LocalDateTime dateTo;

    @NotNull
    private LocalDateTime timeFrom;

    @NotNull
    private LocalDateTime timeTo;

    @NotNull
    @NotEmpty
    private List<Integer> days;

    public Long getScore() {
        long dateScore = Duration.between(dateFrom, dateTo).toHours() * 10000;
        long timeScore = Duration.between(timeFrom.toLocalTime(), timeTo.toLocalTime()).toHours() * 1000;
        long daysScore = days.size();
        long sc = (dateScore + timeScore + daysScore) * -1;
        log.info("dateScore={}, timeScore={} dayScore={}, totalScore={}", dateScore, timeScore, daysScore, sc);
        return sc;
    }

    public Boolean isActive(LocalDateTime nowDate) {
        boolean isDate = nowDate.isAfter(dateFrom) && nowDate.isBefore(dateTo);
        boolean isTime = nowDate.toLocalTime().isAfter(timeFrom.toLocalTime()) && nowDate.toLocalTime().isBefore(timeTo.toLocalTime());
        boolean isDay = days.contains(nowDate.getDayOfWeek().getValue());
        log.info("isDate={} isTime={} isDay={}", isDate, isTime, isDay);
        return isDate && isTime && isDay;
    }

}
