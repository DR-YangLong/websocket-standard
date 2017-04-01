package io.github.yanglong.demo.filter;

import io.github.yanglong.demo.protocol.CustomTextMessage;
import io.github.yanglong.demo.protocol.Message;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * package: io.github.yanglong.demo.filter <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
public class DefaultBusinessFilter implements BusinessFilter{

    @Override
    public void doBusinessFilter(Message message, WebSocketSession session, BusinessFilterChain chain) throws IOException{
        if(message instanceof CustomTextMessage){
            session.sendMessage(new TextMessage("filterChain works!"));
        }
    }
}
