package com.gymin.exercise.stock.form;

import com.gymin.exercise.stock.model.ItemType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class ItemForm {

    // 상품 ID
    private Long id;

    // 상품명
    private String itemName;

    // 가격
    private Integer price;

    // 수량
    private Integer quantity;

    // 유통기한
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    // 판매 여부
    private Boolean open;

    // 판매 채널
    private List<String> channels;

    // 보관 방법
    private ItemType itemType;

    // 배송 방식
    private String delivery;

}
