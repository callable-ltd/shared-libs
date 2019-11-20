package uk.co.vibe.viva.shared.dto.pbx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.vibe.viva.shared.dto.events.PbxAttachResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PbxEventsDTO {
    private String id;
    private String pbxId;
    private LocalDateTime created;
    private LocalDateTime lastUpdated;
    private List<PbxEventDTO> events;
    public PbxEventsDTO(PbxAttachResponse response) {
        this.id = response.getCallId();
        this.created = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.events = new ArrayList<>();
    }
}
