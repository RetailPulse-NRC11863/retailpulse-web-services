package com.retailpulse.platform.shared.interfaces.rest.transform;

import org.springframework.http.ResponseEntity;
import java.net.URI;

public class ResponseEntityAssembler {
    public static <T> ResponseEntity<T> created(String path, Long id, T body) {
        return ResponseEntity.created(URI.create(path + "/" + id)).body(body);
    }
}
