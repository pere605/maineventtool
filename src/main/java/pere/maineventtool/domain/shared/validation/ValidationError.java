package pere.maineventtool.domain.shared.validation;

import org.springframework.lang.Nullable;

public class ValidationError {
    private String message;
    private String value;

    ValidationError(String message, String value) {
        this.message = message;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    @Nullable
    public String getValue() {
        return value;
    }
}
