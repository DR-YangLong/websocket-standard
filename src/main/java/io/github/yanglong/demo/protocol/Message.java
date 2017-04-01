package io.github.yanglong.demo.protocol;

import java.io.Serializable;

/**
 * package: io.github.yanglong.demo.protocol <br/>
 * functional describe:base custom protocol
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
public class Message implements Serializable{
    private static final long serialVersionUID = 1035861145832823031L;
    private static final String DEFAULT_VERSION="1.0";
    /**
     * service version
     */
    private String version=DEFAULT_VERSION;
    /**
     * field describe the service
     */
    private String type;
    /**
     * transform target class name
     */
    private String dataType;

    /**
     * token to http
     */
    private String token;

    public Message() {
    }

    public Message(String type, String dataType) {
        this(DEFAULT_VERSION,type,dataType);
    }

    public Message(String version, String type, String dataType) {
        this.version = version;
        this.type = type;
        this.dataType = dataType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
