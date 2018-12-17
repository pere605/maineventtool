package pere.maineventtool.domain.shared.validation;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidationService {
    public static ValidationErrorCollection parseResult(BindingResult validationResult) {
        ValidationErrorCollection errorCollection = new ValidationErrorCollection();
        for (FieldError error : validationResult.getFieldErrors()) {
            errorCollection.addError(error);
        }

        return errorCollection;
    }
}
