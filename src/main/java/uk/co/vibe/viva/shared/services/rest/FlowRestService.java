package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.flow.FlowPostRequest;
import uk.co.vibe.viva.shared.dto.flow.FlowPutRequest;
import uk.co.vibe.viva.shared.dto.flow.FlowResponse;

public class FlowRestService extends InternalRestService<FlowResponse, FlowPostRequest, FlowPutRequest> {

    private static final String BASE_URL = "http://ivr-service/ivr/flow";

    public FlowRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    public CreatedResponse post(FlowPostRequest payload) {
        return super.post(payload, BASE_URL);
    }

    public void put(FlowPutRequest payload) {
        super.put(payload, BASE_URL);
    }

    @Override
    public FlowResponse get(String id) {
        return super.get(BASE_URL + "/" + id);
    }

    public FlowResponse get() {
        return super.get(BASE_URL);
    }

    public void delete(String id) {
        super.delete(BASE_URL + "/" + id);
    }


}
