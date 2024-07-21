package com.finut.finut_server.controller;

import com.finut.finut_server.config.auth.dto.SessionUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Tag(name = "Home Controller", description = "홈, 로그인, 로그아웃 관련 api")
@Controller
public class HomeController {

    private final HttpSession httpSession;

    public HomeController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
    @Operation(summary = "초기 화면", description = "로그인을 위한 초기 화면을 구성합니다.")
    @GetMapping("/")
    public String home(Model model) {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("username", user.getName());
            model.addAttribute("email", user.getEmail());
        } else {
            System.out.println("User is null");
        }
        return "home";
    }

    @Operation(summary = "로그인", description = "로그인을 위한 페이지를 구성합니다.")
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
