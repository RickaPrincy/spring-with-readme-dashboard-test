package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.factory;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.ForbiddenException;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.AuthProvider;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.ReadmeGroup;
import com.ricka.princy.bonjouraurevoir.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role.ROLE_ADMIN;

@Component
@RequiredArgsConstructor
public class ReadmeGroupFactory {
    private final static String ADMIN_LABEL_NAME="admin";
    private final UserRepository userRepository;

    public ReadmeGroup createReadmeGroup(AuthProvider authProvider){
        var principal = authProvider.getPrincipal();

        if(ROLE_ADMIN.equals(principal.getRole())){
            return ReadmeGroup.builder().label(ADMIN_LABEL_NAME).id(principal.getPassword()).build();
        }

        var user = userRepository.findByApiKey(principal.getApiKey()).orElseThrow(ForbiddenException::new);
        return ReadmeGroup
            .builder()
                .label(user.name())
                .id(user.apiKey())
            .build();
    }
}
