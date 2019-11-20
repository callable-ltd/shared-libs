package uk.co.vibe.viva.shared.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.pbx.PbxEventsDTO;

@Service
public class PbxRestService {

    private static final String BASE_URL = "http://pbx-service/pbx";

    @Autowired
    private VivaRestService vivaRestService;

    public PbxEventsDTO get(String id) {
        return vivaRestService.get(BASE_URL, id, null, PbxEventsDTO.class);
    }

}
