package io.github.yanglong.demo.config;

import io.github.yanglong.demo.handler.MessageHandler;
import io.github.yanglong.demo.handler.MessageSelectorHandler;
import io.github.yanglong.demo.handler.OpenCloseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * package: io.github.yanglong.demo.config <br/>
 * functional describe:socket config
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
@Configuration
@EnableWebSocket
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "websocket")
public class WebSocketConfig implements WebSocketConfigurer {
    private String[] endpoint;

    @Autowired
    private MessageSelectorHandler messageSelectorHandler;
    @Autowired
    private OpenCloseHandler openCloseHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
            //must more verify on endpoint paths
            if(endpoint!=null&&endpoint.length>0) {
                registry.addHandler(messageHandler(), endpoint).addInterceptors(new HttpSessionHandshakeInterceptor()).setAllowedOrigins("*").withSockJS();
            }else {
                throw new RuntimeException("must config at least one endpoint!");
            }
    }

    @Bean
    public MessageHandler messageHandler(){
        MessageHandler handler=new MessageHandler();
        handler.setSelectorHandler(messageSelectorHandler);
        handler.setSessionHandler(openCloseHandler);
        return handler;
    }


    public String[] getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String[] endpoint) {
        this.endpoint = endpoint;
    }

    public MessageSelectorHandler getMessageSelectorHandler() {
        return messageSelectorHandler;
    }

    public void setMessageSelectorHandler(MessageSelectorHandler messageSelectorHandler) {
        this.messageSelectorHandler = messageSelectorHandler;
    }

    public OpenCloseHandler getOpenCloseHandler() {
        return openCloseHandler;
    }

    public void setOpenCloseHandler(OpenCloseHandler openCloseHandler) {
        this.openCloseHandler = openCloseHandler;
    }
}
