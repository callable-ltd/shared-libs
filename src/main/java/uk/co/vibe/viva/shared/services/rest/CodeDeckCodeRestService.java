package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.billing.CodeDeckCodePostRequest;
import uk.co.vibe.viva.shared.dto.billing.CodeDeckCodePutRequest;
import uk.co.vibe.viva.shared.dto.billing.CodeDeckResponse;

@Service
public class CodeDeckCodeRestService extends InternalRestService<CodeDeckResponse, CodeDeckCodePostRequest, CodeDeckCodePutRequest> {

    private static final String BASE_URL = "http://billing-service-svc/billing/code-deck";
    private static final String PATH = "codes";

    public CodeDeckCodeRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(CodeDeckCodePostRequest payload, String codeDeckId) {
        return super.post(payload, BASE_URL + "/" + codeDeckId + "/" + PATH);
    }

    @Override
    public void put(CodeDeckCodePutRequest payload, String codeDeckId) {
        super.put(payload, BASE_URL + "/" + codeDeckId + "/" + PATH);
    }

    public void delete(String codeDeckId, String id) {
        super.delete(BASE_URL + "/" + codeDeckId + "/" + PATH + "/" + id);
    }


}
