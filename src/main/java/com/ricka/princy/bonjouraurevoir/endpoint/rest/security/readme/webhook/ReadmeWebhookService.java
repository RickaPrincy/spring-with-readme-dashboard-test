package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.model.User;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.model.SingleUserInfo;
import com.ricka.princy.bonjouraurevoir.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_ADMIN;
import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_COMMUNITY;

@Service
@RequiredArgsConstructor
public class ReadmeWebhookService {
    private final UserRepository userRepository;

    public SingleUserInfo retrieveUserInfoByEmail(String email){
        var user = userRepository.findByEmail(email).orElse(
            User.builder().name(email). email(email).role(ROLE_COMMUNITY).build()
        );

        return SingleUserInfo
            .builder()
                .name(user.name())
                .email(user.email())
                .apiKey(user.apiKey())
                .isAdmin(ROLE_ADMIN.equals(user.role()))
            .build();
    }
}
