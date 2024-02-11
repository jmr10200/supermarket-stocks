package com.gymin.exercise.stock.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class EncodeUtils {

    public static String sha256Encode(String planText) {
        return Hashing.sha256().hashString(planText, StandardCharsets.UTF_8).toString();
    }
}
