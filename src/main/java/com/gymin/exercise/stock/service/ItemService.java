package com.gymin.exercise.stock.service;

import com.gymin.exercise.stock.form.ItemSearchCondition;
import com.gymin.exercise.stock.model.CommercialChannel;
import com.gymin.exercise.stock.model.Item;
import com.gymin.exercise.stock.model.ItemUpdate;
import com.gymin.exercise.stock.repository.ChannelRepository;
import com.gymin.exercise.stock.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ChannelRepository channelRepository;

    /**
     * 상품 검색
     * @param cond 상품 검색 조건 (이름, 가격)
     * @return 검색결과
     */
//    public List<Item> findItemList(ItemSearchCondition cond) {
//        List<Item> items = itemRepository.findAll(cond);
//        return items;
//    }
    public List<Item> findItemListPage(ItemSearchCondition cond, Integer offSet, Integer recordSize) {
        List<Item> items = itemRepository.findByPage(cond, offSet, recordSize);
        return items;
    }

    public int findListTotalCount(ItemSearchCondition cond) {

        int count = itemRepository.totalCount(cond);
        return count;
    }

    public void findPage(ItemSearchCondition condition) {
        // 전체 페이지 수 취득
        int totalCount = itemRepository.totalCount(condition);
    }

    /**
     * 상품 1개 취득 (id)
     * @param id 취득하기위한 상품 ID
     * @return ID 로 취득된 상품 내용
     */
    public List<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    /**
     * 상품 등록
     * @param item 등록 상품 내용
     * @return 등록된 상품 정보
     */
    @Transactional
    public Item saveItem(Item item, List<CommercialChannel> channelList) {

        // item
        Item savedItem = itemRepository.save(item);
        // channel
        if (channelList.size() > 0) {
            for (CommercialChannel channel : channelList) {
                channel.setItemId(savedItem.getId());
            }
            List<CommercialChannel> savedChannel = channelRepository.save(channelList);
        }
        return savedItem;
    }

    /**
     * 상품 수정
     * @param itemId 수정대상 상품 ID
     * @param updateParam 수정 내용
     */
    @Transactional
    public void updateItem(Long itemId, ItemUpdate updateParam, List<CommercialChannel> channelList) {
        // item
        itemRepository.update(itemId, updateParam);
        // channel
        for (CommercialChannel channel : channelList) {
            channelRepository.update(itemId, channel);
        }

    }

    /**
     * 상품 삭제
     * @param itemId 삭제대상 상품 ID
     */
    public void removeItem(Long itemId) {
        itemRepository.delete(itemId);
    }

    public List<CommercialChannel> findChannelsById(Long itemId) {
        return channelRepository.findById(itemId);
    }
}
