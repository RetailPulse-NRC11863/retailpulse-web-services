package com.retailpulse.platform.shared.application.result;

public record ApplicationError(String code, String message) {
    public static ApplicationError notFound(String message) {
        return new ApplicationError("not_found", message);
    }

    public static ApplicationError validation(String message) {
        return new ApplicationError("validation_error", message);
    }
}
