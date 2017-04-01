package io.github.yanglong.demo.protocol;

/**
 * package: io.github.yanglong.demo.protocol <br/>
 * functional describe:custom text protocol
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
public class CustomTextMessage extends Message{
    private String text;


    public CustomTextMessage() {
        super("service.login","customTextMessage");
    }

    public CustomTextMessage(String text) {
        super("service.login","customTextMessage");
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
