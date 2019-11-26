package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.billing.ProductTableProductPostRequest;
import uk.co.vibe.viva.shared.dto.billing.ProductTableProductPutRequest;
import uk.co.vibe.viva.shared.dto.billing.ProductTableResponse;

@Service
public class ProductTableProductRestService extends InternalRestService<ProductTableResponse, ProductTableProductPostRequest, ProductTableProductPutRequest> {

    private static final String BASE_URL = "http://billing-service-svc/billing/product-table";
    private static final String PATH = "products";

    public ProductTableProductRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(ProductTableProductPostRequest payload, String productTableId) {
        return super.post(payload, BASE_URL + "/" + productTableId + "/" + PATH);
    }

    @Override
    public void put(ProductTableProductPutRequest payload, String productTableId) {
        super.put(payload, BASE_URL + "/" + productTableId + "/" + PATH);
    }

    public void delete(String productTableId, String id) {
        super.delete(BASE_URL + "/" + productTableId + "/" + PATH + "/" + id);
    }


}
