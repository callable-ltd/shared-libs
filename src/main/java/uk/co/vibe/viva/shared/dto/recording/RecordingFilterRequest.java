package uk.co.vibe.viva.shared.dto.recording;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordingFilterRequest {

    private Set<String> ids;

    private String caller;

    private String called;

    private String extension;

    @Min(0)
    private Long minDuration;

    @Min(1)
    private Long maxDuration;

    @NotNull
    private LocalDateTime start;

    @NotNull
    private LocalDateTime end;

    @Min(0)
    @NotNull
    private Integer page;

    @Min(1)
    @Max(100)
    @NotNull
    private Integer max;

    @Pattern(regexp = "^(ASC|DESC)$")
    private String sort;

    private String sortField;

    private Set<String> projections;

    private Set<String> excludeTags;

    public List<Field> getRegexProps() {
        return Stream.of(
                new Field("caller", caller),
                new Field("called", called),
                new Field("extension", extension))
                .filter(Field::isNotBlank)
                .collect(Collectors.toList());
    }

    public Pageable getPageable() {
        return PageRequest.of(page, max);
    }

    public boolean hasPageable() {
        return page != null && max != 0;
    }

    public boolean hasRegex() {
        return !CollectionUtils.isEmpty(getRegexProps());
    }

    public boolean hasIds() {
        return !CollectionUtils.isEmpty(ids);
    }

    public boolean hasProjections() {
        return !CollectionUtils.isEmpty(projections);
    }

    public boolean hasSort() {
        return StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(sortField);
    }

    public boolean hasDurations() {
        return getMinDuration() != null || getMaxDuration() != null;
    }

    @Getter
    public static class Field {

        private String key;
        private String value;

        Field(String key, String value) {
            this.key = key;
            this.value = value;
        }

        boolean isNotBlank() {
            return StringUtils.isNotBlank(this.value);
        }
    }
}
