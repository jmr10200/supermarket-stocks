package com.gymin.exercise.stock.controller;

import com.gymin.exercise.stock.constants.Constants;
import com.gymin.exercise.stock.form.*;
import com.gymin.exercise.stock.model.*;
import com.gymin.exercise.stock.service.ItemService;
import com.gymin.exercise.stock.utils.BaseTableUtils;
import com.gymin.exercise.stock.utils.FormConvertUtils;
import com.gymin.exercise.stock.utils.TableDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/stocks/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * @ModelAttribute 등록, 상세, 수정 폼에서 모두 보여주기 위함
     * channels 을 modelAttribute 에 추가
     */
    @ModelAttribute("channels")
    public Map<String, String> channels() {
        Map<String, String> channels = CommercialChannel.getCommercialChannel();
        return channels;
    }

    /**
     * @ModelAttribute
     * ENUM 타입 이용
     */
    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values(); // 배열반환
    }

    /**
     * @ModelAttribute
     * 자바 객체 이용 방법
     */
    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = DeliveryCode.getDeliveryCode();
        return deliveryCodes;
    }

    @ModelAttribute("listRange")
    public Map<Integer, String> listRange() {
        Map<Integer, String> listRange = ItemSearchCondition.setRange();
        return listRange;
    }

    /**
     * 상품 리스트 초기표시
     * @param itemSearch
     * @param model
     * @return
     */
    @GetMapping
    public String items(@ModelAttribute("itemSearch") ItemSearchCondition itemSearch,
                        BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            itemSearch.initMaxPrice();
        }
        int page = 1;
        int recordSize = Constants.PAGE_ROW_10;

        // 전체 페이지 수 취득
        int totalCount = itemService.findListTotalCount(itemSearch);

        model.addAttribute("page", page);
        model.addAttribute("totalPages", (totalCount / recordSize) + 1);
        model.addAttribute("recordSize", recordSize);

        int offset = (page - 1) * recordSize;

        List<Item> items = itemService.findItemListPage(itemSearch, offset, recordSize);
        List<ItemListForm> itemListForm = FormConvertUtils.itemListToFormList(items);
        model.addAttribute("itemListForm", itemListForm);
        log.info("display item list");
        return "stocks/items";
    }

    /**
     * 상품리스트 검색, 페이징
     * @param itemSearch
     * @param bindingResult
     * @param model
     * @return
     */
    @PostMapping
    public String itemsSearch(@ModelAttribute("itemSearch") ItemSearchCondition itemSearch,
                              BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            itemSearch.initMaxPrice();
        }
        int page = 1;
        if (itemSearch.getPageNum() != null) {
            page = itemSearch.getPageNum();
        }
        if (itemSearch.getLastFlag() == 1) {
            page = itemSearch.getTotalPageNum();
        }
        int recordSize = itemSearch.getSelectedRange();
        // 전체 페이지 수 취득
        int totalCount = itemService.findListTotalCount(itemSearch);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", (totalCount / recordSize) + 1);
        model.addAttribute("recordSize", recordSize);

        int offset = (page - 1) * recordSize;

        List<Item> items = itemService.findItemListPage(itemSearch, offset, recordSize);
        List<ItemListForm> itemListForm = FormConvertUtils.itemListToFormList(items);
        model.addAttribute("itemListForm", itemListForm);
        model.addAttribute("itemSearch", itemSearch);
        log.info("search item list");
        return "stocks/items";
    }

    /**
     * 상품 상세 표시
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        List<Item> item = itemService.findById(itemId);
        ItemForm itemForm = FormConvertUtils.itemsDtoToForm(item);
        model.addAttribute("itemForm", itemForm);
        log.info("display item detail");
        return "stocks/item";
    }

    /**
     * 상품 등록폼 표시
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("itemForm", new ItemAddForm());
        log.info("display item add");
        return "stocks/addForm";
    }

    /**
     * 상품 등록
     * @param itemAddForm
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("itemForm") ItemAddForm itemAddForm, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, HttpServletRequest request) {

        // validation check
        validateAddForm(itemAddForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "stocks/addForm";
        }

        // 세션에서 로그인 유저 정보 취득
        Member session = (Member) request.getSession().getAttribute(Constants.LOGIN_MEMBER_SESSION);
        String loginId = session.getLoginId();

        // validation check OK
        Item target = FormConvertUtils.addFormToItemDto(itemAddForm);
        BaseTableUtils.insertCommonItem(target, loginId);

        List<CommercialChannel> channelList = TableDataUtils.createChannels(target.getChannels(), loginId);
        Item savedItem = itemService.saveItem(target, channelList);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        log.info("add item");
        return "redirect:/stocks/items/{itemId}";
    }

    /**
     * 상품 수정폼 표시
     * @param itemId 수정 대상 ID
     * @param model
     * @return
     */
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        List<Item> item = itemService.findById(itemId);
        // 수정폼 매핑
        ItemEditForm itemEditForm = FormConvertUtils.itemsDtoToEditForm(item);
        model.addAttribute("itemForm", itemEditForm);
        log.info("display item edit");
        return "stocks/editForm";
    }

    /**
     * 상품 수정
     * @param itemId 수정 대상 ID
     * @param itemEditForm
     * @return
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("itemForm") ItemEditForm itemEditForm,
                       BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        // validation check
        validateEditForm(itemEditForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "stocks/editForm";
        }

        // 세션 정보
        Member session = (Member) request.getSession().getAttribute(Constants.LOGIN_MEMBER_SESSION);
        String loginId = session.getLoginId();

        // 수정전 데이터
        List<Item> exItem = itemService.findById(itemId);

        // 원래 데이터 취득
        List<CommercialChannel> exData = itemService.findChannelsById(itemId);

        // 판매채널
        List<CommercialChannel> channelList = TableDataUtils.createUpdateChannels(exData, itemEditForm.getChannels(), loginId);

        // item
        ItemUpdate updateDto = FormConvertUtils.itemsDtoToEditForm(itemEditForm);
        updateDto.setVersion(exItem.get(0).getVersion());
        BaseTableUtils.updateCommonItem(updateDto, loginId);

        // 실행
        itemService.updateItem(itemId, updateDto, channelList);

        redirectAttributes.addAttribute("editStatus", true);
        log.info("edit item");
        return "redirect:/stocks/items/{itemId}";
    }

    /**
     * 상품 삭제
     * @param itemId
     * @return
     */
    @GetMapping("/{itemId}/del")
    public String remove(@PathVariable Long itemId) {
        itemService.removeItem(itemId);
        log.info("delete item ID={}", itemId);
        return "redirect:/stocks/items";
    }

    /**
     * 상품 등록 폼 유효성 체크
     */
    private void validateAddForm(ItemAddForm itemAddForm, BindingResult bindingResult) {

        // 상품명 체크
        if (StringUtils.hasText(itemAddForm.getItemName())) {
            if (itemAddForm.getItemName().length() > 20) {
                bindingResult.rejectValue("itemName", "range.itemAddForm.itemName",
                        new Object[]{20}, null);
            }
        }
        // 가격 체크
        if (itemAddForm.getPrice() != null) {
            if (itemAddForm.getPrice() < 100 || itemAddForm.getPrice() > 1000000) {
                bindingResult.rejectValue("price", "range.itemAddForm.price",
                        new Object[]{1000, 1000000}, null);
            }
        }
        // 수량 체크
        if (itemAddForm.getQuantity() != null) {
            if (itemAddForm.getQuantity() > 9999) {
                bindingResult.rejectValue("quantity", "max.itemAddForm.quantity",
                        new Object[]{9999}, null);
            }
        }

        // 총 최저금액 확인
        if (itemAddForm.getPrice() != null && itemAddForm.getQuantity() != null) {
            int resultPrice = itemAddForm.getPrice() * itemAddForm.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }

    /**
     * 수정 폼 유효성 체크
     */
    private void validateEditForm(ItemEditForm itemEditForm, BindingResult bindingResult) {
        // 상품명 체크
        if (StringUtils.hasText(itemEditForm.getItemName())) {
            if (itemEditForm.getItemName().length() > 20) {
                bindingResult.rejectValue("itemName", "range.itemAddForm.itemName",
                        new Object[]{20}, null);
            }
        }
        // 가격 체크
        if (itemEditForm.getPrice() != null) {
            if (itemEditForm.getPrice() < 100 || itemEditForm.getPrice() > 1000000) {
                bindingResult.rejectValue("price", "range.itemAddForm.price",
                        new Object[]{1000, 1000000}, null);
            }
        }

        // 총 최저금액 확인
        if (itemEditForm.getPrice() != null && itemEditForm.getQuantity() != null) {
            int resultPrice = itemEditForm.getPrice() * itemEditForm.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }

}

