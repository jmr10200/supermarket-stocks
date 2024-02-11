package com.gymin.exercise.stock.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member extends BaseTable {

    private Long id;

    private String loginId;

    private String name;

    private String password;

    private String mailAddress;
}
