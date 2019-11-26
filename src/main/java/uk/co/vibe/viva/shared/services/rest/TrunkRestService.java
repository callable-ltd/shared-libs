package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.customer.TrunkPostRequest;
import uk.co.vibe.viva.shared.dto.customer.TrunkPutRequest;
import uk.co.vibe.viva.shared.dto.customer.TrunkResponse;

public class TrunkRestService extends InternalRestService<TrunkResponse, TrunkPostRequest, TrunkPutRequest> {

    private static final String BASE_URL = "http://customer-service-svc/customers";
    private static final String PATH = "trunks";

    public TrunkRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(TrunkPostRequest payload, String customerId) {
        return super.post(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public void put(TrunkPutRequest payload, String customerId) {
        super.put(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public TrunkResponse get(String customerId) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH);
    }

    public void delete(String customerId, String id) {
        super.delete(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }

    public TrunkResponse get(String customerId, String id) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }


}
