package uk.co.vibe.viva.shared.services.rest;

import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.CreatedResponse;
import uk.co.vibe.viva.shared.dto.customer.ClientDevicePostRequest;
import uk.co.vibe.viva.shared.dto.customer.DevicePostRequest;
import uk.co.vibe.viva.shared.dto.customer.DevicePutRequest;
import uk.co.vibe.viva.shared.dto.customer.DeviceResponse;

public class DeviceRestService extends InternalRestService<DeviceResponse, DevicePostRequest, DevicePutRequest> {

    private static final String BASE_URL = "http://customer-service-svc/customers";
    private static final String PATH = "devices";

    public DeviceRestService(VivaRestService vivaRestService) {
        super(vivaRestService);
    }

    @Override
    public CreatedResponse post(DevicePostRequest payload, String customerId) {
        return super.post(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }


    @Override
    public void put(DevicePutRequest payload, String customerId) {
        super.put(payload, BASE_URL + "/" + customerId + "/" + PATH);
    }

    @Override
    public DeviceResponse get(String customerId) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH);
    }

    public void delete(String customerId, String id) {
        super.delete(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }

    public DeviceResponse get(String customerId, String id) {
        return super.get(BASE_URL + "/" + customerId + "/" + PATH + "/" + id);
    }


}
