package io.github.yanglong.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * package: io.github.yanglong.demo.config <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
@ConfigurationProperties(prefix = "messageType")
@EnableConfigurationProperties
@Configuration
public class MessageTypeMap {
    private HashMap<String,Class> typeMap;

    public HashMap<String, Class> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(HashMap<String, Class> typeMap) {
        this.typeMap = typeMap;
    }

    @Bean
    public MessageTypeMap messageType(){
        return this;
    }
}
