package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
