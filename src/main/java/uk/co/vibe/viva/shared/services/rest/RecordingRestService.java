package uk.co.vibe.viva.shared.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.recording.PublicRecordingResponse;
import uk.co.vibe.viva.shared.dto.recording.RecordingFilterRequest;
import uk.co.vibe.viva.shared.dto.recording.RecordingPutTagRequest;

import java.net.URL;
import java.util.Map;

@Service
public class RecordingRestService {

    private static final String BASE_URL = "http://recording-service-svc";
    private static final String ELEMENT = "recording";

    @Autowired
    private VivaRestService vivaRestService;

    public PublicRecordingResponse find(RecordingFilterRequest request) {
        return vivaRestService.postForEntity(request, BASE_URL + "/" + ELEMENT + "/filter", RecordingFilterRequest.class, PublicRecordingResponse.class);
    }

    public PublicRecordingResponse get(String id, Map<String, String> projections) {
        return vivaRestService.get(BASE_URL + "/" + ELEMENT + "/" + id, projections, PublicRecordingResponse.class);
    }

    public void update(RecordingPutTagRequest recording) {
        vivaRestService.put(recording, BASE_URL + "/" + ELEMENT + "/" + recording.getId() + "/tag/" + recording.getTag(), RecordingPutTagRequest.class);
    }

    public URL url(String id) {
        return vivaRestService.get(BASE_URL + "/" + ELEMENT + "/" + id + "/url", null, URL.class);
    }

    public void restore(String id) {
        vivaRestService.put(BASE_URL + "/" + ELEMENT + "/" + id + "/restore");
    }
}
