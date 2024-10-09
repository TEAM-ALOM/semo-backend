package com.semo.semo.domain.user.service;

import com.semo.semo.domain.user.model.entity.User;
import com.semo.semo.domain.user.model.request.UserSignupReq;
import com.semo.semo.domain.user.model.response.UserSignupRes;
import com.semo.semo.domain.user.repository.UserReposiotry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserReposiotry userReposiotry;

    public UserSignupRes signup(UserSignupReq request){

        User user = User.builder()
                .user_id(request.getUser_id())
                .pw(request.getPw())
                .name(request.getName())
                .nickname(request.getNickname())
                .build();

        User result = userReposiotry.save(user);
        return new UserSignupRes().toDto(result);
    }
}
