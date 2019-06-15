package pere.maineventtool.domain.user.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import pere.maineventtool.domain.user.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {
    private UserRepository repository;

    @Autowired
    public UniqueUserValidator(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return this.repository.findByEmail(value) == null;
    }
}
