package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ReadmeEntryQuery(
    String name,
    String value
) implements Serializable {}
