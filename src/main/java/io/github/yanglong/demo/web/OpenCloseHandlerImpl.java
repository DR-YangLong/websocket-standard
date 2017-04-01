package io.github.yanglong.demo.web;

import io.github.yanglong.demo.handler.OpenCloseHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * package: io.github.yanglong.demo.web <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/30 9:14
 */
@Component
public class OpenCloseHandlerImpl implements OpenCloseHandler {
    public static final ConcurrentHashMap<String, UserDetailsDomain> USER_MAP = new ConcurrentHashMap<>();
    public static final String TOKEN_IN_SESSION_KEY = "web_token";

    @Override
    public void processSession(WebSocketSession socketSession) throws IOException {
        if (getUser(socketSession) != null) {
            getUser(socketSession).setSocketSession(socketSession);
        } else {
            closedConnection(socketSession);
        }
    }

    @Override
    public void clearSession(WebSocketSession socketSession, CloseStatus status) throws IOException {
        //what ever connection closed,the channel unused,remove from principal
        if(getUser(socketSession)!=null){
            getUser(socketSession).setSocketSession(null);
        }
    }

    /**
     * take token form session
     *
     * @param socketSession session
     * @return token string
     */
    private String getTokenInSession(WebSocketSession socketSession) {
        String query = socketSession.getUri().getQuery();
        Object obj = socketSession.getAttributes().get(TOKEN_IN_SESSION_KEY);
        return obj == null ? query : obj.toString();
    }

    /**
     * close connection
     *
     * @param socketSession session
     */
    private void closedConnection(WebSocketSession socketSession) throws IOException {
        socketSession.sendMessage(new TextMessage("unAuthenticated！connection will be closed!"));
        socketSession.close(CloseStatus.NOT_ACCEPTABLE);
    }

    /**
     * get user form session
     *
     * @param socketSession session
     * @return user
     */
    private UserDetailsDomain getUser(WebSocketSession socketSession) {
        String token = getTokenInSession(socketSession);
        if (!StringUtils.isEmpty(token)) {
            return USER_MAP.get(token);
        }
        return null;
    }
}
