package com.gymin.exercise.stock.utils;

import com.gymin.exercise.stock.model.BaseTable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BaseTableUtils {

    public static void insertCommonItem(BaseTable baseTable, String insertUserId) {
        baseTable.setCreatedAt(getTransactionTimeNow());
        baseTable.setUpdatedAt(getTransactionTimeNow());
        baseTable.setCreatedUser(insertUserId);
        baseTable.setUpdatedUser(insertUserId);
        baseTable.setVersion(0);
    }

    public static void updateCommonItem(BaseTable baseTable, String updateUserId) {
        baseTable.setUpdatedAt(getTransactionTimeNow());
        baseTable.setUpdatedUser(updateUserId);
        baseTable.setVersion(baseTable.getVersion() + 1);
    }

    private static LocalDateTime getTransactionTimeNow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        return LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
