package io.github.yanglong.demo.handler;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * package: io.github.yanglong.demo.handler <br/>
 * functional describe:when open the connected,verify token and store session.<br/>
 * when connection closed,clear session.
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/30
 */
public interface OpenCloseHandler {

    /**
     * process business with session when connection ready
     *
     * @param socketSession session
     */
    void processSession(WebSocketSession socketSession) throws IOException;

    /**
     * clear session when connection closed
     *
     * @param socketSession session
     * @throws IOException
     */
    void clearSession(WebSocketSession socketSession,CloseStatus status) throws IOException;
}
