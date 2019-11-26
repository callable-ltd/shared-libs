package uk.co.vibe.viva.shared.services.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.customer.*;

import java.util.Map;

@Slf4j
@Service
public class CustomerRestService {

    private static final String BASE_URL = "http://customer-service-svc/customers";

    @Autowired
    private  VivaRestService vivaRestService;

    public CustomerResponse get(String id, Map<String, String> projections) {
        return vivaRestService.get(BASE_URL, id, projections, CustomerResponse.class);
    }

    public CustomerResponse find(Map<String, String> query) {
        return vivaRestService.find(BASE_URL, query, CustomerResponse.class);
    }

    public void update(CustomerPutRequest CustomerPutRequest) {
        vivaRestService.put(CustomerPutRequest, BASE_URL, CustomerPutRequest.class);
    }

    public CustomerDTO save(CustomerPostRequest CustomerPostRequest) {
        return vivaRestService.postForEntity(CustomerPostRequest, BASE_URL, CustomerPostRequest.class, CustomerDTO.class);
    }

    public void delete(String id) {
        vivaRestService.delete(BASE_URL, id);
    }

    public CustomerResponse find(CustomerFilter customerFilter) {
        return vivaRestService.postForEntity(customerFilter, BASE_URL + "/filter", CustomerFilter.class, CustomerResponse.class);
    }


}
