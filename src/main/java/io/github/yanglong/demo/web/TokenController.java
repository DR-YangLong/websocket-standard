package io.github.yanglong.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

import static io.github.yanglong.demo.web.OpenCloseHandlerImpl.TOKEN_IN_SESSION_KEY;
import static io.github.yanglong.demo.web.OpenCloseHandlerImpl.USER_MAP;

/**
 * package: io.github.yanglong.demo.web <br/>
 * functional describe:tag the user info with token and store in a map.<br/>
 * user will take from the map where WebSocket connected, then set the session property.
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/30 9:07
 */
@RestController
@RequestMapping("/web")
public class TokenController {


    @RequestMapping("/token")
    public UserDetailsDomain getToken(HttpSession session) {
        Object obj = session.getAttribute(TOKEN_IN_SESSION_KEY);
        UserDetailsDomain userDetails;
        if (obj == null) {
            String token = UUID.randomUUID().toString();
            userDetails = new UserDetailsDomain();
            userDetails.setToken(token);
            userDetails.setUserName("Test");
            userDetails.setPassword("password");
            session.setAttribute(TOKEN_IN_SESSION_KEY, token);
            USER_MAP.put(token, userDetails);
        } else {
            userDetails = USER_MAP.get(obj.toString());
        }
        return userDetails;
    }

    @RequestMapping("users")
    public ModelAndView userList(){
        ModelAndView mv=new ModelAndView("users");
        mv.addObject("users", USER_MAP);
        return mv;
    }

    @RequestMapping(value = "sendMsg",params = "token")
    public ModelAndView sendMsg(String token){
        ModelAndView mv=new ModelAndView("sendMsg");
        mv.addObject("token",token);
        return mv;
    }

    @RequestMapping(value = "submitMsg",params = {"token","message"})
    public String submitMsg(String token,String message){
        if(USER_MAP.get(token)!=null&& USER_MAP.get(token).getSocketSession()!=null){
            try {
                USER_MAP.get(token).getSocketSession().sendMessage(new TextMessage(message));
            } catch (IOException e) {
                return "Internal Server Error";
            }
        }
        return "success";
    }

    @RequestMapping(value = "offLine",params = "token")
    public String offLine(String token){
        if(USER_MAP.get(token)!=null&& USER_MAP.get(token).getSocketSession()!=null){
            try {
                USER_MAP.get(token).getSocketSession().close(CloseStatus.SESSION_NOT_RELIABLE);
            } catch (IOException e) {
                return "Internal Server Error";
            }
        }
        return "success";
    }
}
