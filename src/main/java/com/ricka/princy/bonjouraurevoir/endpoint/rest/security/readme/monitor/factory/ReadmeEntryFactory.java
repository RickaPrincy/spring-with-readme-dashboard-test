package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.factory;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.entry.ReadmeEntry;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.entry.ReadmeEntryRequest;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.entry.ReadmeEntryResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.Protocol;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
public class ReadmeEntryFactory {
    public ReadmeEntry createReadmeEntry(HttpServletRequest request, HttpServletResponse response, Instant startedDatetime, Instant endedDatetime) {
        return ReadmeEntry
            .builder()
                .pareRef(request.getRequestURI())
                .startedDateTime(startedDatetime)
                .time(Duration.between(startedDatetime, endedDatetime).toMillis())
                .request(ReadmeEntryRequest.builder()
                    .url(request.getRequestURL().toString())
                    .method(request.getMethod())
                    .httpVersion(Protocol.HTTP_2.toString())
                    .headers(List.of()) // TODO: map this
                    .queryString(List.of()) // TODO: map this
                    .build()
                )
                .response(ReadmeEntryResponse.builder()
                    .status(response.getStatus())
                    .headers(List.of()) // TODO: map this
                    .statusText(HttpStatus.valueOf(response.getStatus()).name())
                    .content(null) // TODO: map this
                    .build()
                )
            .build();
    }
}
