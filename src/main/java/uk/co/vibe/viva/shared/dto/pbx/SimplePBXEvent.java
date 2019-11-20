package uk.co.vibe.viva.shared.dto.pbx;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplePBXEvent {
    private String id;
    private String customerId;
    private String from;
    private String to;
    private String component;
    private String componentId;
    private String extension;
    private String event;
    private String direction;
    private long timestamp;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        SimplePBXEvent pbxEvent = (SimplePBXEvent) obj;
        return id.equals(pbxEvent.id) &&
                componentId.equals(pbxEvent.componentId) &&
                component.equals(pbxEvent.component) &&
                event.equals(pbxEvent.event) &&
                extension.equals(pbxEvent.extension);
    }

}
