package com.retailpulse.platform.shared.interfaces.rest.transform;

import com.retailpulse.platform.shared.interfaces.rest.resources.ErrorResource;
import java.time.OffsetDateTime;
import java.util.List;

public class ErrorResponseAssembler {
    public static ErrorResource toResource(String code, String message, List<String> details) {
        return new ErrorResource(code, message, details, OffsetDateTime.now());
    }
}
