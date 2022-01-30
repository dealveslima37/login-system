package com.cromms.loginsystem.dto;

import com.cromms.loginsystem.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "preencha o campo nome")
    @Size(min = 3, max = 50, message = "O campo nome deve conter entre 3 e 50 caracteres")
    private String name;

    @Email(message = "Digite uma email válido")
    private String email;

    @NotEmpty(message = "preencha o campo telefone")
    @Size(min = 11, max = 20, message = "O campo phone deve conter entre 11 e 20 caracteres")
    private String phone;

    @NotEmpty(message = "preencha o campo pasword")
    @Size(min = 8, max = 50, message = "O campo senha deve conter entre 8 e 50 caracteres")
    private String password;

    private String profile;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
