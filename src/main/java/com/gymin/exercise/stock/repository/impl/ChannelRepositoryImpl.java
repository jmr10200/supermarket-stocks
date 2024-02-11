package com.gymin.exercise.stock.repository.impl;

import com.gymin.exercise.stock.model.CommercialChannel;
import com.gymin.exercise.stock.repository.ChannelRepository;
import com.gymin.exercise.stock.repository.mapper.ChannelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ChannelRepositoryImpl implements ChannelRepository {

    private final ChannelMapper channelMapper;

    @Override
    public List<CommercialChannel> save(List<CommercialChannel> channels) {
        channelMapper.save(channels);
        return channels;
    }

    @Override
    public void update(Long itemId, CommercialChannel channel) {
        channelMapper.upsert(itemId, channel);
    }

    @Override
    public List<CommercialChannel> findById(Long itemId) {
        return channelMapper.findById(itemId);
    }
}
