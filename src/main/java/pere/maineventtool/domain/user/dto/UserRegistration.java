package pere.maineventtool.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class UserRegistration {
    @NotBlank
    @Email
    private String email; //TODO Add validation for uniqueness
    @NotBlank
    @Length(min = 8)
    private String password;
}
