package com.semo.semo.domain.user.controller;

import com.semo.semo.domain.user.model.request.UserSignupReq;
import com.semo.semo.domain.user.model.response.UserSignupRes;
import com.semo.semo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserSignupReq request){
        try {
            UserSignupRes signupRes = userService.signup(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(signupRes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signup failed : " +e.getMessage());
        }

    }

}
