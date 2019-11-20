package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.billing.CodeDeckPostRequest;
import uk.co.vibe.viva.shared.dto.billing.CodeDeckPutRequest;
import uk.co.vibe.viva.shared.dto.billing.CodeDeckResponse;

public class CodeDeckRestService extends InternalRestService<CodeDeckResponse, CodeDeckPostRequest, CodeDeckPutRequest> {

    private static final String BASE_URL = "http://billing-service/billing/code-deck";

    public CodeDeckRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    public CreatedResponse post(CodeDeckPostRequest payload) {
        return super.post(payload, BASE_URL);
    }

    public void put(CodeDeckPutRequest payload) {
        super.put(payload, BASE_URL);
    }

    @Override
    public CodeDeckResponse get(String id) {
        return super.get(BASE_URL + "/" + id);
    }

    public CodeDeckResponse get() {
        return super.get(BASE_URL);
    }

    public void delete(String id) {
        super.delete(BASE_URL + "/" + id);
    }


}
