package com.gymin.exercise.stock.repository.mapper;

import com.gymin.exercise.stock.form.ItemSearchCondition;
import com.gymin.exercise.stock.model.Item;
import com.gymin.exercise.stock.model.ItemUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemMapper {

    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam") ItemUpdate updateParam);

    List<Item> findById(Long id);

//    List<Item> findAll(ItemSearchCondition itemSearch);

    void deleteById(Long id);

    int totalCount(ItemSearchCondition itemSearch);

    List<Item> findByPage(@Param("param") ItemSearchCondition itemSearch, @Param("offSet") Integer offSet, @Param("recordSize") Integer recordSize);

}