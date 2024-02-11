package com.gymin.exercise.stock.utils;

import com.gymin.exercise.stock.constants.Constants;
import com.gymin.exercise.stock.model.CommercialChannel;

import java.util.*;

public class TableDataUtils {

    /**
     * 판매채널 등록
     */
    public static List<CommercialChannel> createChannels(List<String> channels, String userId) {
        List<CommercialChannel> channelList = new ArrayList<>();

        for (String name : channels) {
            CommercialChannel ch = new CommercialChannel();
            BaseTableUtils.insertCommonItem(ch, userId);
            ch.setUseYn(Constants.USE_YES);
            ch.setChannelName(name);

            channelList.add(ch);
        }
        return channelList;
    }

    /**
     * 판매채널 수정
     */
    public static List<CommercialChannel> createUpdateChannels(List<CommercialChannel> exData, 
                                                               List<String> channels, String userId) {
        List<CommercialChannel> channelList = new ArrayList<>();

        // 기존 data
        Map<String, CommercialChannel> exDataMap = new HashMap<>();
        Map<String, String> checkedData = new HashMap<>();
        for (CommercialChannel ex : exData) {
            exDataMap.put(ex.getChannelName(), ex);
            checkedData.put(ex.getChannelName(), ex.getUseYn());
        }

        // 사용 데이터에서 기존 데이터에 없으면 insert
        for (String ch : channels) {
            if (!exDataMap.containsKey(ch)) {
                // insert
                CommercialChannel insertData = new CommercialChannel();
                insertData.setUseYn(Constants.USE_YES);
                insertData.setChannelName(ch);
                BaseTableUtils.insertCommonItem(insertData, userId);
                channelList.add(insertData);
                // 데이터에 추가
                checkedData.put(ch, Constants.USE_YES);
            }
        }

        // 사용 확인
        for (String checked : checkedData.keySet()) {
            // 사용 리스트에 있으면 사용 O
            if (checkedData.get(checked).equals(Constants.USE_NO) && channels.contains(checked)) {
                // 기존 데이터 있음 && yn = 0 && 사용 O 이면, 갱신 대상
                CommercialChannel val = exDataMap.get(checked);
                val.setUseYn(Constants.USE_YES);
                BaseTableUtils.updateCommonItem(val, userId);
            } else if (checkedData.get(checked).equals(Constants.USE_YES) && !channels.contains(checked)) {
                // 기존 데이터 있음 && yn = 1 && 사용 X 이면, 갱신 대상
                CommercialChannel val = exDataMap.get(checked);
                val.setUseYn(Constants.USE_NO);
                BaseTableUtils.updateCommonItem(val, userId);
            }
            // (기존 데이터 있음 && yn = 0 && 사용 X) 또는 (기존 데이터 있음 && yn = 1 && 사용 O) 이면, 갱신 X
        }

        for (CommercialChannel value : exDataMap.values()) {
            channelList.add(value);
        }

        return channelList;
    }

}
