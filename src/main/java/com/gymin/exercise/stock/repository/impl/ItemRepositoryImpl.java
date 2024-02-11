package com.gymin.exercise.stock.repository.impl;

import com.gymin.exercise.stock.form.ItemSearchCondition;
import com.gymin.exercise.stock.model.Item;
import com.gymin.exercise.stock.model.ItemUpdate;
import com.gymin.exercise.stock.repository.ItemRepository;
import com.gymin.exercise.stock.repository.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemMapper itemMapper;

    @Override
    public Item save(Item item) {
        itemMapper.save(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdate updateParam) {
        itemMapper.update(itemId, updateParam);
    }

    @Override
    public List<Item> findById(Long id) {
        return itemMapper.findById(id);
    }

//    @Override
//    public List<Item> findAll(ItemSearchCondition condition) {
//        return itemMapper.findAll(condition);
//    }

    @Override
    public void delete(Long itemId) {
        itemMapper.deleteById(itemId);
    }

    @Override
    public int totalCount(ItemSearchCondition condition) {
        return itemMapper.totalCount(condition);
    }

    @Override
    public List<Item> findByPage(ItemSearchCondition condition, Integer offSet, Integer recordSize) {
        return itemMapper.findByPage(condition, offSet, recordSize);
    }
}