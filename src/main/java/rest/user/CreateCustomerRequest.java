package rest.user;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class CreateCustomerRequest extends CreateUserRequest {

    @NotBlank(message = "Password cannot be empty")
    private String password;

    public CreateCustomerRequest() {
    }

    public CreateCustomerRequest(String email, String firstName, String lastName, String password) {
        super(email, firstName, lastName);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
