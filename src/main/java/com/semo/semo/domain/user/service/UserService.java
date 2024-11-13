package com.semo.semo.domain.user.service;

import com.semo.semo.domain.user.model.entity.User;
import com.semo.semo.domain.user.model.request.UserLoginReq;
import com.semo.semo.domain.user.model.request.UserSignupReq;
import com.semo.semo.domain.user.model.response.UserLoginRes;
import com.semo.semo.domain.user.model.response.UserSignupRes;
import com.semo.semo.domain.user.repository.UserRepository;
import com.semo.semo.global.error.CustomException;
import com.semo.semo.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserSignupRes signup(UserSignupReq request){
        // 중복 체크
        userRepository.findByUserId(request.getId())
                .ifPresent(x ->{
                    throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
                });
        User user = User.builder()
                .userId(request.getId())
                .name(request.getName())
                .nickname(request.getNickname())
                .role(request.getRole())
                .build();

        User result = userRepository.save(user);
        return new UserSignupRes().toDto(result);
    }
}
