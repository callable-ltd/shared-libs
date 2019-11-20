package uk.co.vibe.viva.shared.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.customer.CustomerAddressPutRequest;

@Service
public class CustomerAddressRestService {

    private static final String BASE_URL = "http://customer-service/customers";
    private static final String ELEMENT = "address";

    @Autowired
    private VivaRestService vivaRestService;

    public void update(CustomerAddressPutRequest request) {
        vivaRestService.put(request, BASE_URL + "/" + request.getCustomerId() + "/" + ELEMENT, CustomerAddressPutRequest.class);
    }
}
