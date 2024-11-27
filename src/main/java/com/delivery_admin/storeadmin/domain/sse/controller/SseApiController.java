package com.delivery_admin.storeadmin.domain.sse.controller;

import com.delivery_admin.storeadmin.domain.authorization.model.UserSession;
import com.delivery_admin.storeadmin.domain.sse.connection.SseConnectionPool;
import com.delivery_admin.storeadmin.domain.sse.connection.model.UserSseConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseApiController {

    private final SseConnectionPool sseConnectionPool;
    private final ObjectMapper objectMapper;

    @GetMapping(path = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession
    ) {
        log.info("login user {}", userSession);

        UserSseConnection userSseConnection = UserSseConnection.connect(
                userSession.getStoreId().toString(),
                sseConnectionPool,
                objectMapper
        );

        sseConnectionPool.addSession(userSseConnection.getUniqueKey(), userSseConnection);

        return userSseConnection.getSseEmitter();

        // connection test
//        SseEmitter sseEmitter = new SseEmitter(1000L * 60);
//        userConnection.put(userSession.getUserId().toString(), sseEmitter);
//
//        UserSseConnection temp = UserSseConnection.connect(
//                userSession.getStoreId().toString(),
//                sseConnectionPool
//        );
//
//        sseEmitter.onTimeout(() -> {
//            log.info("on timed out");
//            // 클라이언트와 타임아웃이 일어났을때
//            sseEmitter.complete();
//        });
//
//        sseEmitter.onCompletion(() -> {
//            log.info("completion");
//            // 클라이언트와 연결이 종료 되었을때 하는 작업
//            userConnection.remove(userSession.getUserId().toString());
//        });
//
//        // 최초 연결시 응답 전송
//        var event = SseEmitter
//                .event()
//                .name("onopen");
////                .data("connect");
//
//        try {
//            sseEmitter.send(event);
//        } catch (IOException e) {
//            sseEmitter.completeWithError(e);
//        }
//
//        return sseEmitter;
    }

    @GetMapping("/push-event")
    public void pushEvent(
            @Parameter(hidden = true) @AuthenticationPrincipal UserSession userSession
    ) {
        UserSseConnection userSseConnection = sseConnectionPool.getSession(userSession.getStoreId().toString());

        Optional.ofNullable(userSseConnection)
                .ifPresent(it -> {
                    it.sendMessage("hello world - mason");
                });

        // connection test
//        // 기존에 연결된 유저 찾기
//        var emitter = userConnection.get(userSession.getUserId().toString());
//        var event = SseEmitter.event().data("hello");  // onmessage에 전달
//
//        try {
//            emitter.send(event);
//        } catch (IOException e) {
//            emitter.completeWithError(e);
//        }
    }
}
