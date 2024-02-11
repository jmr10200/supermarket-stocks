package com.gymin.exercise.stock.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.gymin.exercise.stock.constants.Constants.*;

/**
 * 배송방식 CLASS
 * FAST : 빠른 배송
 * NORMAL : 일반 배송
 * ECONOMIC : 경제적인 배송
 */
@Data
public class DeliveryCode {

    private String code;

    private String deliveryName;

    private DeliveryCode(String code, String deliveryName) {
        this.code = code;
        this.deliveryName = deliveryName;
    }

    public static List<DeliveryCode> getDeliveryCode() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode(DELIVERY_CODE_FAST,DELIVERY_NAME_FAST));
        deliveryCodes.add(new DeliveryCode(DELIVERY_CODE_NORMAL, DELIVERY_NAME_NORMAL));
        deliveryCodes.add(new DeliveryCode(DELIVERY_CODE_ECONOMIC, DELIVERY_NAME_ECONOMIC));

        return deliveryCodes;
    }

}
