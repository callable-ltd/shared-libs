package uk.co.vibe.viva.shared.services.rest;

import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.billing.ProductTablePostRequest;
import uk.co.vibe.viva.shared.dto.billing.ProductTablePutRequest;
import uk.co.vibe.viva.shared.dto.billing.ProductTableResponse;

public class ProductTableRestService extends InternalRestService<ProductTableResponse, ProductTablePostRequest, ProductTablePutRequest> {

    private static final String BASE_URL = "http://billing-service-svc/billing/product-table";

    public ProductTableRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    public CreatedResponse post(ProductTablePostRequest payload) {
        return super.post(payload, BASE_URL);
    }

    public void put(ProductTablePutRequest payload) {
        super.put(payload, BASE_URL);
    }

    @Override
    public ProductTableResponse get(String id) {
        return super.get(BASE_URL + "/" + id);
    }

    public ProductTableResponse get() {
        return super.get(BASE_URL);
    }

    public void delete(String id) {
        super.delete(BASE_URL + "/" + id);
    }


}
