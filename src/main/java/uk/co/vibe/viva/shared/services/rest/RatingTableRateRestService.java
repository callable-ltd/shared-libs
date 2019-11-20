package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.billing.RatingTableRatePostRequest;
import uk.co.vibe.viva.shared.dto.billing.RatingTableRatePutRequest;
import uk.co.vibe.viva.shared.dto.billing.RatingTableResponse;

@Service
public class RatingTableRateRestService extends InternalRestService<RatingTableResponse, RatingTableRatePostRequest, RatingTableRatePutRequest> {

    private static final String BASE_URL = "http://billing-service/billing/rating-table";
    private static final String PATH = "rates";

    public RatingTableRateRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(RatingTableRatePostRequest payload, String ratingTableId) {
        return super.post(payload, BASE_URL + "/" + ratingTableId + "/" + PATH);
    }

    @Override
    public void put(RatingTableRatePutRequest payload, String ratingTableId) {
        super.put(payload, BASE_URL + "/" + ratingTableId + "/" + PATH);
    }

    public void delete(String ratingTableId, String id) {
        super.delete(BASE_URL + "/" + ratingTableId + "/" + PATH + "/" + id);
    }


}
