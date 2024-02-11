package com.gymin.exercise.stock.repository;

import com.gymin.exercise.stock.model.CommercialChannel;
import com.gymin.exercise.stock.model.Item;

import java.util.List;

public interface ChannelRepository {

    List<CommercialChannel> save(List<CommercialChannel> channels);

    void update(Long itemId, CommercialChannel channel);

    List<CommercialChannel> findById(Long itemId);
}
