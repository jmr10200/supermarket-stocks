package com.gymin.exercise.stock.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 상품 수정 DTO
 */
@Data
public class ItemUpdate extends BaseTable {

    private String itemName;

    private Integer price;

    private Integer quantity;

    // 판매 여부
    private Boolean open;

    // 유통 기한
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expirationDate;

    // 배송 방식
    private String delivery;

    // 판매 채널
    private List<String> channels;

    private ItemType itemType;

    private String itemTypeName;

    public ItemUpdate() {
    }

    public ItemUpdate(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemTypeName() {
        return itemType.getCodeName();
    }
}