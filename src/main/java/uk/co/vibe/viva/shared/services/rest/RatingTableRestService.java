package uk.co.vibe.viva.shared.services.rest;

import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.billing.RatingTablePostRequest;
import uk.co.vibe.viva.shared.dto.billing.RatingTablePutRequest;
import uk.co.vibe.viva.shared.dto.billing.RatingTableResponse;

public class RatingTableRestService extends InternalRestService<RatingTableResponse, RatingTablePostRequest, RatingTablePutRequest> {

    private static final String BASE_URL = "http://billing-service/billing/rating-table";

    public RatingTableRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    public CreatedResponse post(RatingTablePostRequest payload) {
        return super.post(payload, BASE_URL);
    }

    public void put(RatingTablePutRequest payload) {
        super.put(payload, BASE_URL);
    }

    @Override
    public RatingTableResponse get(String id) {
        return super.get(BASE_URL + "/" + id);
    }

    public RatingTableResponse get() {
        return super.get(BASE_URL);
    }

    public void delete(String id) {
        super.delete(BASE_URL + "/" + id);
    }


}
