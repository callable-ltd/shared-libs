package uk.co.vibe.viva.shared.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@NoArgsConstructor
public class Created {

    private URI uri;

    public Created(URI uri) {
        this.uri = uri;
    }

    public String getId() {
        return uri.getPath().substring(uri.getPath().lastIndexOf('/') + 1);
    }

}
