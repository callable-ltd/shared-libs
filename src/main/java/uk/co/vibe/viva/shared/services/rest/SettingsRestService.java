package uk.co.vibe.viva.shared.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.customer.*;

import java.net.URI;

public class SettingsRestService extends InternalRestService<SettingsResponse, SettingsPostRequest, SettingsPutRequest> {

    private static final String BASE_URL = "http://customer-service/customers";
    private static final String PATH = "settings";

    public SettingsRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(SettingsPostRequest payload, String customerId) {
        return super.post(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public void put(SettingsPutRequest payload, String customerId) {
        super.put(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public SettingsResponse get(String customerId) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH);
    }

    public void delete(String customerId, String id) {
        super.delete(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }

    public SettingsResponse get(String customerId, String id) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }


}
