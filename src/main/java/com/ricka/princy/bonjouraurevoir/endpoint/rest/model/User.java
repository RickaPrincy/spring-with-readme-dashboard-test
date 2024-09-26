package com.ricka.princy.bonjouraurevoir.endpoint.rest.model;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role;
import lombok.Builder;

@Builder
public record User(String apiKey, String name, Role role) { }
