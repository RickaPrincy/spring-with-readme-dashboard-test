package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.webhook.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record CreateWebhook(String email, String readmeProject) implements Serializable {}
