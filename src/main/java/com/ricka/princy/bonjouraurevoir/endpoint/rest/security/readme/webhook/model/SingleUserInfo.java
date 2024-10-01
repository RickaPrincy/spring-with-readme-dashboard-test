package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record SingleUserInfo(String apiKey, String name, String email, Boolean isAdmin) implements Serializable {}
