package io.github.yanglong.demo.web;

import org.springframework.web.socket.WebSocketSession;

import java.io.Serializable;

/**
 * package: io.github.yanglong.demo.web <br/>
 * functional describe:a demo principal
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/30 10:01
 */
public class UserDetailsDomain implements Serializable{
    private static final long serialVersionUID = -5784931605995659471L;
    private String userName;
    private String token;
    private WebSocketSession socketSession;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WebSocketSession getSocketSession() {
        return socketSession;
    }

    public void setSocketSession(WebSocketSession socketSession) {
        this.socketSession = socketSession;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
