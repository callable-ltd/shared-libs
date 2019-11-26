package uk.co.vibe.viva.shared.services.rest;

import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.billing.PlatformRatesPostRequest;
import uk.co.vibe.viva.shared.dto.billing.PlatformRatesPutRequest;
import uk.co.vibe.viva.shared.dto.billing.PlatformRatesResponse;

public class PlatformRatesRestService extends InternalRestService<PlatformRatesResponse, PlatformRatesPostRequest, PlatformRatesPutRequest> {

    private static final String BASE_URL = "http://billing-service-svc/billing/platform-rates";

    public PlatformRatesRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    public CreatedResponse post(PlatformRatesPostRequest payload) {
        return super.post(payload, BASE_URL);
    }

    public void put(PlatformRatesPutRequest payload) {
        super.put(payload, BASE_URL);
    }

    @Override
    public PlatformRatesResponse get(String id) {
        return super.get(BASE_URL + "/" + id);
    }

    public PlatformRatesResponse get() {
        return super.get(BASE_URL);
    }

    public void delete(String id) {
        super.delete(BASE_URL + "/" + id);
    }


}
