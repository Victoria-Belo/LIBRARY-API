package com.project.library.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Victoria
 */
public class UserAuthenticationDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;

    private String auth;

    public UserAuthenticationDTO() { }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth){
        this.auth = auth;
    }

}
