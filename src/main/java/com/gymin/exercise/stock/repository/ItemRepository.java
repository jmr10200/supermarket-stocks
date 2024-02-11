package com.gymin.exercise.stock.repository;


import com.gymin.exercise.stock.form.ItemSearchCondition;
import com.gymin.exercise.stock.model.Item;
import com.gymin.exercise.stock.model.ItemUpdate;

import java.util.List;

public interface ItemRepository {

    Item save(Item item);

    void update(Long itemId, ItemUpdate updateParam);

    List<Item> findById(Long id);

//    List<Item> findAll(ItemSearchCondition cond);

    void delete(Long itemId);

    int totalCount(ItemSearchCondition itemSearch);

    List<Item> findByPage(ItemSearchCondition itemSearch, Integer offSet, Integer recordSize);
}
