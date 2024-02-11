package com.gymin.exercise.stock.form;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ItemListForm {

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    private LocalDate expirationDate;

    private String displayDate;


}
