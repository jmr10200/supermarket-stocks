package com.gymin.exercise.stock.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class Item extends BaseTable{

    private Long id;

    private String itemName;

    private Integer price;

    private Integer quantity;

    // 유통 기한
    private LocalDate expirationDate;

    // 판매 여부
    private Boolean open;

    // 판매 채널
    private List<String> channels;

    // 보관 방법
    private ItemType itemType;

    // 배송 방식
    private String delivery;

    // 판매 채널
    private String channelName;

    private String useYn;

    private String itemTypeName;
}
