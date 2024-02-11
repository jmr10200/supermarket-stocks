package com.gymin.exercise.stock.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty(message = "{required.loginForm.loginId}")
    private String loginId;

    @NotEmpty(message = "{required.loginForm.password}")
    private String password;
}
