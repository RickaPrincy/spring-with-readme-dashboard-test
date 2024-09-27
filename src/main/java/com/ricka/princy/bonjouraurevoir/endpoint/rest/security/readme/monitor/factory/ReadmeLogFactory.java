package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.factory;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.AuthProvider;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.ReadmeMonitorConf;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.ReadmeLog;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.ReadmeRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.List;

import static com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.ReadmeRequest.ReadmeRequestLog;

@Component
@RequiredArgsConstructor
public class ReadmeLogFactory {
    private final ReadmeGroupFactory readmeGroupFactory;
    private final ReadmeEntryFactory readmeEntryFactory;
    private final ReadmeRequestCreatorFactory readmeRequestCreatorFactory;
    private final AuthProvider authProvider;

    public ReadmeLog createReadmeLog(
        HttpServletRequest request,
        HttpServletResponse response,
        Instant startedDatetime,
        Instant endedDatetime,
        ReadmeMonitorConf readmeMonitorConf
    ){
        return ReadmeLog.builder()
            .clientIPAddress(request.getRemoteAddr())
            .development(readmeMonitorConf.isDevelopment())
            .group(readmeGroupFactory.createReadmeGroup(authProvider))
            .request(ReadmeRequest.builder().log(ReadmeRequestLog.builder()
                .entries(List.of(readmeEntryFactory.createReadmeEntry(request, response, startedDatetime, endedDatetime)))
                .creator(readmeRequestCreatorFactory.createReadmeRequestCreator(readmeMonitorConf))
                .build()).build())
            .build();
    }
}