package uk.co.vibe.viva.shared.services.rest;

import org.springframework.core.GenericTypeResolver;
import uk.co.vibe.viva.shared.dto.CreatedResponse;

public class InternalRestService<T, U, V> {

    private final VivaRestService vivaRestService;
    private final Class<U> u;
    private final Class<V> v;
    private final Class<T> t;

    @SuppressWarnings("unchecked")
    public InternalRestService(VivaRestService vivaRestService) {
        this.vivaRestService = vivaRestService;
        Class<?>[] args = GenericTypeResolver.resolveTypeArguments(getClass(), InternalRestService.class);
        assert args != null && args.length == 3;
        this.t = (Class<T>) args[0];
        this.u = (Class<U>) args[1];
        this.v = (Class<V>) args[2];
    }

    public CreatedResponse post(U payload, String path) {
        return vivaRestService.postForEntity(payload, path, u);
    }

    public void put(V payload, String path) {
        vivaRestService.put(payload, path, v);
    }

    public T get(String path) {
        return vivaRestService.get(path, null, t);
    }

    public void delete(String path) {
        vivaRestService.delete(path);
    }


}
