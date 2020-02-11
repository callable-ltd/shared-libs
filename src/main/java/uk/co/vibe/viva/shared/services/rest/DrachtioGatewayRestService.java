package uk.co.vibe.viva.shared.services.rest;

import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.customer.pbx.SipGateway;
import uk.co.vibe.viva.shared.dto.sbc.CreateGatewayRequest;
import uk.co.vibe.viva.shared.dto.sbc.UpdateGatewayRequest;

public class DrachtioGatewayRestService extends InternalRestService<SipGateway, CreateGatewayRequest, UpdateGatewayRequest> {

    private static final String BASE_URL = "http://drachtio-service-svc/gateway";

    public DrachtioGatewayRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(CreateGatewayRequest payload, String customerId) {
        return super.post(payload, BASE_URL);
    }

    @Override
    public void put(UpdateGatewayRequest payload, String customerId) {
        super.put(payload, BASE_URL);
    }

    @Override
    public SipGateway get(String id) {
        return super.get(BASE_URL + "/" + id);
    }

    public void delete(String id) {
        super.delete(BASE_URL + "/" + id);
    }


}
