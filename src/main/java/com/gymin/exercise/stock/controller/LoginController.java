package com.gymin.exercise.stock.controller;

import com.gymin.exercise.stock.constants.Constants;
import com.gymin.exercise.stock.form.LoginForm;
import com.gymin.exercise.stock.model.Member;
import com.gymin.exercise.stock.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 로그인 페이지 표시
     *
     * @param loginForm
     * @return
     */
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login/loginForm";
    }

    /**
     * 로그인
     *
     * @param loginForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult,
                        HttpServletRequest request) {

        // validation
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (Objects.isNull(loginMember)) {
            bindingResult.reject("loginFail", "ID 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 세션 있으면 얻고 없으면 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 정보 저정
        session.setAttribute(Constants.LOGIN_MEMBER_SESSION, loginMember);

        return "redirect:/";
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 세션 삭제
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
