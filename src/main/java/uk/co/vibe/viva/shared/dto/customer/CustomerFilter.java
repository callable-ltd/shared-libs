package uk.co.vibe.viva.shared.dto.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerFilter {
    private Set<String> ids;
    private String name;
    private String ddi;
    private String ip;
    private String vendor;
    private String zone;
    private String username;
    private String extension;
    private String mac;


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

    public List<Field> getRegexProps() {
        return Stream.of(
                new Field("name", name),
                new Field("ddis.name", ddi),
                new Field("trunks.ip", ip),
                new Field("trunks.vendor", vendor),
                new Field("trunks.zone", zone),
                new Field("devices.client.username", username),
                new Field("devices.extension", extension),
                new Field("devices.mac", mac))
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

    public void addIds(List<String> idsToAdd) {
        if (ids == null)
            ids = new HashSet<>();
        ids.addAll(idsToAdd);
    }

    @Data
    @NoArgsConstructor
    public static class Field {

        private String key;
        private String value;

        public Field(String key, String value) {
            this.key = key;
            this.value = value;
        }

        boolean isNotBlank() {
            return StringUtils.isNotBlank(this.value);
        }
    }
}
