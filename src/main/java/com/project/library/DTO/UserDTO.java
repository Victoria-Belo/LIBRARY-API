package com.project.library.DTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(groups = {UserDTOValidation.Create.class, UserDTOValidation.UpdateEmail.class})
    @Email(groups = {UserDTOValidation.Create.class, UserDTOValidation.UpdateEmail.class})
    private String email;

    @NotBlank(groups = {UserDTOValidation.Create.class, UserDTOValidation.UpdatePassword.class})
    @Size(min = 5, groups = {UserDTOValidation.Create.class, UserDTOValidation.UpdatePassword.class}, message = "Password must have at least 5 characters.")
    private String password;

    private String role;

    public UserDTO(){}

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
