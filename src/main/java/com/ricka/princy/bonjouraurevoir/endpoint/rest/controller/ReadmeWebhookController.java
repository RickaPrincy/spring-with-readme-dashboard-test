package com.ricka.princy.bonjouraurevoir.endpoint.rest.controller;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.ReadmeWebhookService;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.ReadmeWebhookValidator;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.ReadmeWebhookConf;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.model.CreateWebhook;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.model.SingleUserInfo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReadmeWebhookController {
    private final ReadmeWebhookValidator readmeWebhookValidator;
    private final ReadmeWebhookConf readmeWebhookConf;
    private final ReadmeWebhookService service;

    @PostMapping("/webhook")
    SingleUserInfo webhook(@RequestBody CreateWebhook body, HttpServletRequest request){
        readmeWebhookValidator.accept(body, request, readmeWebhookConf);
        return service.retrieveUserInfoByEmail(body.email());
    }
}
