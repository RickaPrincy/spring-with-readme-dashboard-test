package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.ReadmeGroup;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.ReadmeLog;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class ReadmeLogFactory {
    public ReadmeLog createReadmeLog(
        ServletRequest request,
        ServletResponse response,
        ReadmeMonitorConf conf,
        long requestDuration
    ){
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

//        return ReadmeLog
//                .builder()
//                .clientIPAddress("192.168.0.125")
//                .development(true)
//                .group(ReadmeGroup.builder().label("demos").email("ricka@gmail.com").id("api_api_key").build())
//                .request(
//                        CreateRequest.builder()
//                                .log(CreateRequest.Log
//                                        .builder()
//                                        .creator(Creator.builder().version("1.2.1").name("readmeio").build())
//                                        .entries(List.of(
//                                                Entry.builder()
//                                                        .pageref(path)
//                                                        .startedDateTime(now)
//                                                        .time(duration)
//                                                        .request(
//                                                                Entry.Request
//                                                                        .builder()
//                                                                        .url(path)
//                                                                        .method(HttpMethod.GET.toString())
//                                                                        .httpVersion(HttpClient.newHttpClient().version().toString())
//                                                                        .headers(List.of(Entry.Header.builder().value("ric").name("test").build()))
//                                                                        .queryString(List.of(Entry.Query.builder().value("ric").name("lol").build()))
//                                                                        .build()
//                                                        )
//                                                        .response(Entry.Response
//                                                                .builder()
//                                                                .status(status)
//                                                                .headers(List.of(Entry.Header.builder().value("Authorization").name("api_api").build()))
//                                                                .statusText("SUCCESS")
//                                                                .content(Entry.ResponseContent.builder().text("hello").mimeType("text/plain").build())
//                                                                .build())
//                                                        .build()
//                                        ))
//                                        .build())
//                                .build()
//                )
//                .build()

        return null;
    }
}
