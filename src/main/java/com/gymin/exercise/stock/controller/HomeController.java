package com.gymin.exercise.stock.controller;

import com.gymin.exercise.stock.constants.Constants;
import com.gymin.exercise.stock.model.Member;
import com.gymin.exercise.stock.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @Autowired
    HomeService homeService;

    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = Constants.LOGIN_MEMBER_SESSION, required = false) Member loginMember, Model model) {

        // login x : home
        if (loginMember == null) {
            return "home";
        }

        // login
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
