package com.ricka.princy.bonjouraurevoir.endpoint.rest.model;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.model.Authority.Role;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record User(String apiKey, String email, String name, Role role) implements Serializable  { }
