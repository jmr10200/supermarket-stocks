package com.gymin.exercise.stock.form;

import com.gymin.exercise.stock.constants.Constants;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Item 검색 조건 Model
 */
@Data
public class ItemSearchCondition {
    // 검색 키워드
    private String itemName;

    private Integer maxPrice;

    private Integer selectedRange;

    //    private Map<Integer, String> listRange;
    private Integer pageNum;
    private Integer displaySize;
//    private Integer pgNum;
    private Integer totalPageNum;
    private Integer lastFlag;

    public ItemSearchCondition() {

    }

    public ItemSearchCondition(String itemName, Integer maxPrice) {
        this.itemName = itemName;
        this.maxPrice = maxPrice;
//        this.listRange = setRange();
        // default
        this.pageNum = 1;
        this.displaySize = Constants.PAGE_ROW_10;
    }

    public void initMaxPrice() {
        this.maxPrice = null;
    }

    private Map<Integer, String> setRange() {
        Map<Integer, String> listRange = new LinkedHashMap<>();
        listRange.put(Constants.PAGE_ROW_10, "10개 보기");
        listRange.put(Constants.PAGE_ROW_30, "30개 보기");
        listRange.put(Constants.PAGE_ROW_50, "50개 보기");
        return listRange;
    }

}