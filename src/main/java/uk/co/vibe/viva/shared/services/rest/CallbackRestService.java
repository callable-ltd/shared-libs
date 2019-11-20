package uk.co.vibe.viva.shared.services.rest;

import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.customer.CallbackPostRequest;
import uk.co.vibe.viva.shared.dto.customer.CallbackPutRequest;
import uk.co.vibe.viva.shared.dto.customer.CallbackResponse;

public class CallbackRestService extends InternalRestService<CallbackResponse, CallbackPostRequest, CallbackPutRequest> {

    private static final String BASE_URL = "http://customer-service/customers";
    private static final String PATH = "callbacks";

    public CallbackRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(CallbackPostRequest payload, String customerId) {
        return super.post(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public void put(CallbackPutRequest payload, String customerId) {
        super.put(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public CallbackResponse get(String customerId) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH);
    }

    public void delete(String customerId, String id) {
        super.delete(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }

    public CallbackResponse get(String customerId, String id) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }

}
