package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.customer.DDIPostRequest;
import uk.co.vibe.viva.shared.dto.customer.DDIPutRequest;
import uk.co.vibe.viva.shared.dto.customer.DdiResponse;

public class DDIRestService extends InternalRestService<DdiResponse, DDIPostRequest, DDIPutRequest> {

    private static final String BASE_URL = "http://customer-service/customers";
    private static final String PATH = "ddis";

    public DDIRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(DDIPostRequest payload, String customerId) {
        return super.post(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public void put(DDIPutRequest payload, String customerId) {
        super.put(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public DdiResponse get(String customerId) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH);
    }

    public void delete(String customerId, String id) {
        super.delete(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }

    public DdiResponse get(String customerId, String id) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }

}
