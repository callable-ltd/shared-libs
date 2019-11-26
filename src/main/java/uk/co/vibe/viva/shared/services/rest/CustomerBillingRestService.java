package uk.co.vibe.viva.shared.services.rest;

import uk.co.vibe.viva.shared.dto.billing.CustomerBillingPostRequest;
import uk.co.vibe.viva.shared.dto.billing.CustomerBillingPutRequest;
import uk.co.vibe.viva.shared.dto.billing.CustomerBillingResponse;

public class CustomerBillingRestService extends InternalRestService<CustomerBillingResponse, CustomerBillingPostRequest, CustomerBillingPutRequest> {

    private static final String BASE_URL = "http://billing-service-svc/billing/customer-billing";

    public CustomerBillingRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    public void put(CustomerBillingPutRequest payload) {
        super.put(payload, BASE_URL);
    }

    @Override
    public CustomerBillingResponse get(String customerId) {
        return super.get(BASE_URL + "/" + customerId);
    }

    public CustomerBillingResponse get() {
        return super.get(BASE_URL);
    }
}
