package com.gymin.exercise.stock.controller;

import com.gymin.exercise.stock.constants.Constants;
import com.gymin.exercise.stock.form.MemberAddForm;
import com.gymin.exercise.stock.model.Member;
import com.gymin.exercise.stock.service.MemberService;
import com.gymin.exercise.stock.utils.BaseTableUtils;
import com.gymin.exercise.stock.utils.FormConvertUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("memberAddForm") MemberAddForm memberAddForm) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute("memberAddForm") MemberAddForm memberAddForm, BindingResult bindingResult,
                       HttpServletRequest request) {

        // validation check
        validateAddForm(memberAddForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        // 폼 -> DTO 변환
        Member target = FormConvertUtils.addFormToMember(memberAddForm);
        Member session = (Member) request.getSession().getAttribute(Constants.LOGIN_MEMBER_SESSION);
        String loginId = "";
        if (session != null) {
            loginId = session.getLoginId();
        }
        BaseTableUtils.insertCommonItem(target, loginId);
        Member savedMember = memberService.saveMember(target);

        // savedMember 가 null 이면 등록된 아이디 있음
        if (Objects.isNull(savedMember)) {
            bindingResult.reject("loginFail", "입력한 내용을 확인해주세요.");
            bindingResult.rejectValue("loginId", "member.addMemberForm.loginId",
                    null, null);
            return "members/addMemberForm";
        }
        return "redirect:/";
    }

    /**
     * 등록 폼 유효성체크
     */
    private void validateAddForm(MemberAddForm memberAddForm, BindingResult bindingResult) {

        // 필수체크
        if (StringUtils.hasText(memberAddForm.getLoginId())) {
            if (memberAddForm.getLoginId().length() > 30) {
                bindingResult.rejectValue("loginId", "range.memberAddForm.loginId",
                        new Object[]{30}, null);
            }
        }

        if (StringUtils.hasText(memberAddForm.getName())) {
            if (memberAddForm.getName().length() > 60) {
                bindingResult.rejectValue("name", "range.memberAddForm.name",
                        new Object[]{60}, null);
            }
        }

        if (StringUtils.hasText(memberAddForm.getEmailAddress())) {
            if (memberAddForm.getEmailAddress().length() > 80) {
                bindingResult.rejectValue("mailAddress", "range.memberAddForm.mainAddress",
                        new Object[]{80}, null);
            }
        }
    }
}
