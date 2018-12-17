package pere.maineventtool.domain.shared.validation;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValidationErrorCollection {
    private Map<String, ArrayList<ValidationError>> errors = new HashMap<>();

    public void addError(FieldError error) {
        if (!errors.containsKey(error.getField())) {
            errors.put(error.getField(), new ArrayList<>());
        }

        errors.get(error.getField()).add(
            new ValidationError(
                error.getDefaultMessage(),
                error.getRejectedValue() != null ? error.getRejectedValue().toString() : null
            )
        );
    }

    public Map<String, ArrayList<ValidationError>> getErrors() {
        return this.errors;
    }
}
