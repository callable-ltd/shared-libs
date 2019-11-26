package uk.co.vibe.viva.shared.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.vibe.viva.shared.dto.stat.SimpleXY;
import uk.co.vibe.viva.shared.dto.stat.SimpleXYWrapper;
import uk.co.vibe.viva.shared.dto.stat.StatRequest;

import java.util.List;

@Service
public class StatRestService {

    private static final String BASE_URL = "http://stat-service-svc";

    @Autowired
    private VivaRestService vivaRestService;

    public List<SimpleXY> filter(StatRequest statRequest) {
        return vivaRestService.postForEntity(statRequest, BASE_URL + "/stat", StatRequest.class, SimpleXYWrapper.class)
                .getData();
    }
}
