package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.exception.InternalServerErrorException;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.factory.ReadmeLogFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReadmeMonitor {
    private final static String README_AUTH_PREFIX = "Basic ";
    private final static String README_API_MIME_TYPE = "application/json";
    private static final OkHttpClient README_CLIENT = new OkHttpClient();
    private final ObjectMapper objectMapper;
    private final ReadmeMonitorConf readmeMonitorConf;
    private final ReadmeLogFactory readmeLogFactory;

    public void saveLog(HttpServletRequest request, HttpServletResponse response, Instant startedDatetime, Instant endedDatetime) throws IOException {
        var readmeLog = readmeLogFactory.createReadmeLog(request, response, startedDatetime, endedDatetime,readmeMonitorConf);
        if(readmeMonitorConf.isDevelopment() != readmeLog.development()){
            throw new InternalServerErrorException("readmeLog.development should be " + readmeMonitorConf.isDevelopment());
        }

        MediaType mediaType = MediaType.parse(README_API_MIME_TYPE);
        RequestBody requestBody = RequestBody.create(objectMapper.writeValueAsString(List.of(readmeLog)), mediaType);
        Request readmeRequest = new Request.Builder()
            .url(readmeMonitorConf.getUrl())
            .header("Content-Type", README_API_MIME_TYPE)
            .header("Authorization", getBasicAuthValue())
            .post(requestBody)
            .build();
        Response readmeResponse = README_CLIENT.newCall(readmeRequest).execute();

        log.info("readme.monitor.requestBody: {}", requestBody);
        log.info("readme.monitor.responseBody: {}", readmeResponse.body().string());
        log.info("readme.monitor.responseStatus : {}", readmeResponse.code());
    }

    private String getBasicAuthValue(){
        String authInfo = readmeMonitorConf.getApiKey() + ":";
        return README_AUTH_PREFIX + Base64.getEncoder().encodeToString(authInfo.getBytes());
    }
}