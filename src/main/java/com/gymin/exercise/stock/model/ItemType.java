package com.gymin.exercise.stock.model;

/**
 * 상품종류 ENUM
 */
public enum ItemType {

    ROOM("ROOM", "상온"),
    FRIDGE("FRIDGE", "냉장"),
    FROZEN("FROZEN", "냉동"),
    ETC("ETC","기타");

    private final String codeName;
    private final String description;

    ItemType(String codeName, String description) {
        this.codeName = codeName;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCodeName() {
        return codeName;
    }
}
