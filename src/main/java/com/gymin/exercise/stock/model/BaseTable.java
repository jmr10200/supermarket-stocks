package com.gymin.exercise.stock.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseTable {

    private LocalDateTime createdAt;

    private String createdUser;

    private LocalDateTime updatedAt;

    private String updatedUser;

    private Integer version;

}
