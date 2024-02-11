package com.gymin.exercise.stock.repository.mapper;

import com.gymin.exercise.stock.model.CommercialChannel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelMapper {

    void save(List<CommercialChannel> channels);

    void upsert(@Param("itemId") Long itemId, @Param("channel") CommercialChannel channel);

    List<CommercialChannel> findById(@Param("itemId") Long itemId);

}
