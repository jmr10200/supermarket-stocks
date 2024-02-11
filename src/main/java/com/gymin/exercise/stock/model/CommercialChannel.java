package com.gymin.exercise.stock.model;

import com.gymin.exercise.stock.constants.Constants;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class CommercialChannel extends BaseTable {

    private Long id;

    private Long itemId;

    private String channelName;

    private String useYn;

    public static Map<String, String> getCommercialChannel() {
        Map<String, String> channels = new LinkedHashMap<>();
        channels.put(Constants.CHANNEL_CODE_STORE, Constants.CHANNEL_NAME_STORE);
        channels.put(Constants.CHANNEL_CODE_OPEN_MARKET, Constants.CHANNEL_NAME_OPEN_MARKET);
        channels.put(Constants.CHANNEL_CODE_ONLINE, Constants.CHANNEL_NAME_ONLINE);
        channels.put(Constants.CHANNEL_CODE_ETC, Constants.CHANNEL_NAME_ETC);
        return channels;
    }

}
