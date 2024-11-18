package com.semo.semo.domain.user.service;

import com.semo.semo.domain.user.model.entity.User;
import com.semo.semo.domain.user.model.request.UserLoginReq;
import com.semo.semo.domain.user.model.request.UserSignupReq;
import com.semo.semo.domain.user.model.response.UserLoginRes;
import com.semo.semo.domain.user.model.response.UserSignupRes;
import com.semo.semo.domain.user.repository.UserRepository;
import com.semo.semo.global.error.CustomException;
import com.semo.semo.global.error.ErrorCode;
import com.semo.semo.global.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserSignupRes signup(UserSignupReq request) {
        // 중복 체크
        userRepository.findByUserId(request.getId())
                .ifPresent(x -> {
                    throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
                });

        // Sejong Auth API 호출 설정
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://auth.imsejong.com/auth?method=DosejongSession";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // 요청 바디 구성
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", request.getId());
        requestBody.put("pw", request.getPw());

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

        // API 호출 및 응답 처리
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && "success".equals(responseBody.get("msg"))) {
            Map<String, Object> result = (Map<String, Object>) responseBody.get("result");
            if (Boolean.TRUE.equals(result.get("is_auth"))) {

                Map<String, Object> body = (Map<String, Object>) result.get("body");
                String major = (String) body.get("major");

                // 성공 시 회원 가입 진행
                User user = User.builder()
                        .userId(request.getId())
                        .name(request.getName())
                        .nickname(request.getNickname())
                        .role(request.getRole())
                        .major(major)
                        .build();

                User savedUser = userRepository.save(user);
                return new UserSignupRes().toDto(savedUser);
            }
        }
        throw new CustomException(ErrorCode.AUTH_FAIL);
    }

    public UserLoginRes login(UserLoginReq request) {
        // 회원 유무 확인
        User user = userRepository.findByUserId(request.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // Sejong Auth API 호출 설정
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://auth.imsejong.com/auth?method=DosejongSession";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // 요청 바디 구성
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", request.getId());
        requestBody.put("pw", request.getPw());

        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(requestBody, headers);

        // API 호출 및 응답 처리
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);

        Map<String, Object> responseBody = response.getBody();
        if (responseBody != null && "success".equals(responseBody.get("msg"))) {
            Map<String, Object> result = (Map<String, Object>) responseBody.get("result");
            if (Boolean.TRUE.equals(result.get("is_auth"))) {

//                 //성공 시 로그인 진행
//                UsernamePasswordAuthenticationToken authenticationToken = new CustomUsernamePasswordAuthenticationToken(request.getId());
//                authenticationToken.setAuthenticated(true);
//                 //자격 증명 확인
//                Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//                authenticateWithoutPassword(request.getId(), authorities);

                String authToken = JwtUtils.generateRefreshToken(request.getId());

                // 인증된 사용자를 가져와 refreshToken을 저장
//                User user = (User) authenticate.getPrincipal();

                user.setRefreshToken(authToken);
                userRepository.save(user);

                return UserLoginRes.builder()
                        .id(user.getUserId())
                        .nickname(user.getNickname())
                        .build();
            }
        }
        throw new CustomException(ErrorCode.AUTH_FAIL);
    }
}
