package io.github.yanglong.demo.handler;

import com.alibaba.fastjson.JSONObject;
import io.github.yanglong.demo.config.BusinessFilterConfig;
import io.github.yanglong.demo.config.MessageTypeMap;
import io.github.yanglong.demo.filter.DefaultBusinessFilterChain;
import io.github.yanglong.demo.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * package: io.github.yanglong.demo.handler <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
@Component
@SuppressWarnings(value = {"unchecked"})
public class MessageSelectorHandlerImpl implements MessageSelectorHandler{

    @Autowired
    private MessageTypeMap messageTypeMap;
    @Autowired
    private BusinessFilterConfig businessFilterConfig;

    @Override
    public void selectorMessage(WebSocketSession session, TextMessage message) throws IOException{
        if(message!=null) {
            String msgStr=message.getPayload();
            JSONObject object = JSONObject.parseObject(msgStr);
            if(object.get("type")==null||object.get("dataType")==null){
            }else {
                Class<? extends Message> clazz=messageTypeMap.getTypeMap().get(object.get("dataType").toString());
                Message msg=JSONObject.parseObject(msgStr,clazz);
                //filter chain to deal msg
                new DefaultBusinessFilterChain(businessFilterConfig).doBusinessFilter(msg,session);
            }
        }
    }

    public MessageTypeMap getMessageTypeMap() {
        return messageTypeMap;
    }

    public void setMessageTypeMap(MessageTypeMap messageTypeMap) {
        this.messageTypeMap = messageTypeMap;
    }
}
