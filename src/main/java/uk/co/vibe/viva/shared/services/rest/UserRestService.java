package uk.co.vibe.viva.shared.services.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.user.*;

import java.util.Map;

@Slf4j
@Service
public class UserRestService {

    private static final String BASE_URL = "http://customer-service/users";

    @Autowired
    private VivaRestService vivaRestService;

    public UserResponse get(String id, Map<String, String> projections) {
        return vivaRestService.get(BASE_URL, id, projections, UserResponse.class);
    }

    public UserResponse find(Map<String, String> query) {
        return vivaRestService.find(BASE_URL, query, UserResponse.class);
    }

    public void update(UserPutRequest userPutRequest) {
        vivaRestService.put(userPutRequest, BASE_URL, UserPutRequest.class);
    }

    public void update(UserPutRolesRequest request) {
        vivaRestService.put(request, BASE_URL + "/roles", UserPutRolesRequest.class);
    }

    public void update(UserPutCustomersRequest request) {
        vivaRestService.put(request, BASE_URL + "/customers", UserPutCustomersRequest.class);
    }

    public void update(UserTokenRequest tokenRequest) {
        vivaRestService.put(tokenRequest, BASE_URL + "/token", UserPutRequest.class);
    }

    public UserDTO save(UserPostRequest userPostRequest) {
        return vivaRestService.postForEntity(userPostRequest, BASE_URL, UserPostRequest.class, UserDTO.class);
    }

    public void delete(String id) {
        vivaRestService.delete(BASE_URL, id);
    }


    public UserResponse find(UserFilterDTO userFilterDTO) {
        return vivaRestService.postForEntity(userFilterDTO, BASE_URL + "/filter", UserFilterDTO.class, UserResponse.class);
    }


}
