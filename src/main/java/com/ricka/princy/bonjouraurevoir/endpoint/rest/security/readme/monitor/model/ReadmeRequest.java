package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.entry.ReadmeEntry;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record ReadmeRequest(
    ReadmeRequestLog log
) implements Serializable {

    @Builder
    @JsonInclude(NON_NULL)
    public record ReadmeRequestLog(
        ReadmeRequestCreator creator,
        List<ReadmeEntry> entries
    ) implements Serializable {}
}
