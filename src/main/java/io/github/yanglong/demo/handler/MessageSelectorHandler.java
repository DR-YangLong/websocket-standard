package io.github.yanglong.demo.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * package: io.github.yanglong.demo.handler <br/>
 * functional describe:business selector
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
public interface MessageSelectorHandler {

    void selectorMessage(WebSocketSession session, TextMessage message) throws IOException;
}
