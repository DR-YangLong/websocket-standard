package io.github.yanglong.demo.handler;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * package: io.github.yanglong.demo.handler <br/>
 * functional describe: message handler
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
public class MessageHandler extends TextWebSocketHandler{

    private MessageSelectorHandler selectorHandler;

    private OpenCloseHandler sessionHandler;

    /**
     * connection ready to do something,eg:store the session
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionHandler.processSession(session);
    }

    /**
     *  transform message and process business
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        selectorHandler.selectorMessage(session,message);
    }

    /**
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        exception.printStackTrace();
    }

    /**
     * clear session
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionHandler.clearSession(session,status);
    }

    public MessageSelectorHandler getSelectorHandler() {
        return selectorHandler;
    }

    public void setSelectorHandler(MessageSelectorHandler selectorHandler) {
        this.selectorHandler = selectorHandler;
    }

    public OpenCloseHandler getSessionHandler() {
        return sessionHandler;
    }

    public void setSessionHandler(OpenCloseHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }
}
