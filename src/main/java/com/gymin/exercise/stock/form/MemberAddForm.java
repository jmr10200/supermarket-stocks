package com.gymin.exercise.stock.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberAddForm {

    private Long id;

    @NotEmpty(message = "{required.addMemberForm.loginId}")
    private String loginId;

    @NotEmpty(message = "{required.addMemberForm.name}")
    private String name;

    @NotEmpty(message = "{required.addMemberForm.password}")
    private String password;

    @NotEmpty(message = "{required.addMemberForm.emailAddress}")
    private String emailAddress;
}
