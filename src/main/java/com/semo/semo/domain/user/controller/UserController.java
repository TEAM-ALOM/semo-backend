package com.semo.semo.domain.user.controller;

import com.semo.semo.domain.user.model.request.UserSignupReq;
import com.semo.semo.domain.user.model.response.UserSignupRes;
import com.semo.semo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody UserSignupReq request){ //TODO : 반환 메세지 정하기
        UserSignupRes signupRes = userService.signup(request);
        //return ResponseEntity.status(HttpStatus.OK);
        return "OK";
    }

}
