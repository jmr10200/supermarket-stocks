package com.gymin.exercise.stock.form;

import com.gymin.exercise.stock.model.ItemType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class ItemAddForm {

    private Long id;

    @NotBlank(message = "{required.itemForm.itemName}")
    private String itemName;

    //소수점 불가
    @NotNull(message = "{required.itemForm.price}")
    private Integer price;

    // 소수점 불가
    @NotNull(message = "{required.itemForm.quantity}")
    private Integer quantity;

    // 유통 기한
    @NotNull(message = "{required.itemForm.expirationDate}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    // 판매 여부
    private Boolean open;

    // 판매 채널
    private List<String> channels;

    // 보관 방법
    @NotNull(message = "{required.itemForm.itemType}")
    private ItemType itemType;

    // 배송 방식
    private String delivery;

}
