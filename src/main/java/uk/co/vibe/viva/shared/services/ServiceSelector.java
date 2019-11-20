package uk.co.vibe.viva.shared.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ServiceSelector {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public URI choose(String serviceId, String uid) {

        List<ServiceInstance> services = discoveryClient.getInstances(serviceId)
                .stream()
                .sorted(Comparator.comparing(ServiceInstance::getInstanceId))
                .collect(Collectors.toList());

        if (services.size() == 0) {
            throw new IllegalStateException("No instances for " + serviceId);
        }
        AtomicInteger grp = new AtomicInteger(0);
        List<Map> grpArray = buildServiceMap(grp, services.size());
        String lastChar = uid.substring(uid.length() - 1);
        int c = lastChar.toCharArray()[0];
        int instance = getInstance(grpArray, c);
        log.debug("Service instance {} selected for uid={}, service={}", services.get(instance).getUri(), uid, serviceId);
        return services.get(instance).getUri();
    }

    public URI choose(String serviceId) {
        return loadBalancerClient.choose(serviceId).getUri();
    }

    private Integer getInstance(List<Map> grpArray, int c) {
        return grpArray.stream()
                .filter(map -> map.get("char").equals(c))
                .findFirst()
                .map(map -> (int) map.get("group"))
                .orElse(0);
    }

    private List<Map> buildServiceMap(AtomicInteger grp, int size) {
        return Stream.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b",
                "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
                "y", "z")
                .map(s -> {
                    if (grp.get() == size)
                        grp.set(0);
                    int lastChar = s.toCharArray()[0];
                    HashMap<String, Integer> map = new HashMap<>();
                    map.put("char", lastChar);
                    map.put("group", grp.getAndIncrement());
                    return map;
                })
                .collect(Collectors.toList());
    }

}
