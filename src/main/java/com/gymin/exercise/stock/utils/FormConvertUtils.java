package com.gymin.exercise.stock.utils;


import com.gymin.exercise.stock.constants.Constants;
import com.gymin.exercise.stock.form.*;
import com.gymin.exercise.stock.model.Item;
import com.gymin.exercise.stock.model.ItemType;
import com.gymin.exercise.stock.model.ItemUpdate;
import com.gymin.exercise.stock.model.Member;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Form 매핑 유틸 클래스
 */
public class FormConvertUtils {

    /**
     * 상세페이지 표시 : item 리스트 -> 폼 변환
     * @param items
     * @return
     */
    public static ItemForm itemsDtoToForm(List<Item> items) {
        ItemForm itemForm = new ItemForm();
        if (items.size() > 0) {

            Item item = items.get(0);
            itemForm.setId(item.getId());
            itemForm.setItemName(item.getItemName());
            itemForm.setPrice(item.getPrice());
            itemForm.setQuantity(item.getQuantity());

            itemForm.setItemType(item.getItemType());
            itemForm.setOpen(item.getOpen());
            itemForm.setDelivery(item.getDelivery());
            itemForm.setExpirationDate(item.getExpirationDate());
            if (!"".equals(item.getItemTypeName())) {
                itemForm.setItemType(ItemType.valueOf(item.getItemTypeName()));
            }

            // 판매채널 : use_yn = 1 인 경우 표시
            List<String> channels = new ArrayList<>();
            for (Item i : items) {
                if (Constants.USE_YES.equals(i.getUseYn())) {
                    channels.add(i.getChannelName());
                }
            }
            itemForm.setChannels(channels);
        }

        return itemForm;
    }

    private ItemType getItemType(String itemTypeCode) {
        return ItemType.valueOf(itemTypeCode);
    }

    /**
     * 복수 item -> 폼 리스트 변환
     * @param itemList
     * @return
     */
    public static List<ItemListForm> itemListToFormList(List<Item> itemList) {
        List<ItemListForm> itemListForm = new ArrayList<>();
        if (itemList.size() > 0) {

            for (Item item : itemList) {
                ItemListForm itemForm = new ItemListForm();

                itemForm.setId(item.getId());
                itemForm.setItemName(item.getItemName());
                itemForm.setPrice(item.getPrice());
                itemForm.setQuantity(item.getQuantity());
                itemForm.setExpirationDate(item.getExpirationDate());
                itemForm.setDisplayDate(item.getExpirationDate().format(DateTimeFormatter.ofPattern("uuuu/MM/dd")));

                itemListForm.add(itemForm);
            }

        }
        return itemListForm;
    }


    /**
     * Add 폼 데이터 Item 변환
     * @param itemAddForm
     * @return
     */
    public static Item addFormToItemDto(ItemAddForm itemAddForm) {
        Item item = new Item();
        item.setId(itemAddForm.getId());
        item.setItemName(itemAddForm.getItemName());
        item.setPrice(itemAddForm.getPrice());
        item.setQuantity(itemAddForm.getQuantity());

        item.setOpen(itemAddForm.getOpen());
        item.setChannels(itemAddForm.getChannels());
        item.setDelivery(itemAddForm.getDelivery());
        item.setItemType(itemAddForm.getItemType());
        item.setItemTypeName(itemAddForm.getItemType().getCodeName());
        item.setExpirationDate(itemAddForm.getExpirationDate());

        return item;
    }

    /**
     * 수정페이지 표시 : item 리스트 -> 폼 변환
     * @param items
     * @return
     */
    public static ItemEditForm itemsDtoToEditForm(List<Item> items) {
        ItemEditForm itemForm = new ItemEditForm();
        if (items.size() > 0) {

            Item item = items.get(0);
            itemForm.setId(item.getId());
            itemForm.setItemName(item.getItemName());
            itemForm.setPrice(item.getPrice());
            itemForm.setQuantity(item.getQuantity());

            if (item.getItemType() != null) {
                itemForm.setItemType(item.getItemType());
            }
            itemForm.setOpen(item.getOpen());
            itemForm.setDelivery(item.getDelivery());
            itemForm.setExpirationDate(item.getExpirationDate());
            if (!"".equals(item.getItemTypeName())) {
                itemForm.setItemType(ItemType.valueOf(item.getItemTypeName()));
            }

            // 판매채널 : use_yn = 1 인 경우 표시
            List<String> channels = new ArrayList<>();
            for (Item i : items) {
                if (Constants.USE_YES.equals(i.getUseYn())) {
                    channels.add(i.getChannelName());
                }
            }
            itemForm.setChannels(channels);
        }

        return itemForm;
    }

    /**
     * 수정페이지 표시 : 폼 -> itemUpdateDto 변환
     * @param editForm
     * @return
     */
    public static ItemUpdate itemsDtoToEditForm(ItemEditForm editForm) {
        ItemUpdate updateDto = new ItemUpdate();

        updateDto.setItemName(editForm.getItemName());
        updateDto.setPrice(editForm.getPrice());
        updateDto.setQuantity(editForm.getQuantity());

        updateDto.setItemType(editForm.getItemType());
        updateDto.setOpen(editForm.getOpen());
        updateDto.setDelivery(editForm.getDelivery());
        updateDto.setExpirationDate(editForm.getExpirationDate());
        updateDto.setItemTypeName(editForm.getItemType().getCodeName());
        if (!"".equals(editForm.getItemTypeName())) {
            updateDto.setItemType(ItemType.valueOf(editForm.getItemTypeName()));
        }
        updateDto.setChannels(editForm.getChannels());

        return updateDto;
    }

    public static Member addFormToMember(MemberAddForm memberAddForm) {
        Member member = new Member();
        member.setLoginId(memberAddForm.getLoginId());
        member.setPassword(memberAddForm.getPassword());
        member.setName(memberAddForm.getName());
        member.setMailAddress(memberAddForm.getEmailAddress());
        return member;
    }
}
