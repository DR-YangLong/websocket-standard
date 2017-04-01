package io.github.yanglong.demo.filter;

import io.github.yanglong.demo.config.BusinessFilterConfig;
import io.github.yanglong.demo.protocol.Message;
import org.springframework.util.CollectionUtils;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * package: io.github.yanglong.demo.filter <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
public class DefaultBusinessFilterChain implements BusinessFilterChain{
    private BusinessFilterConfig filterConfig;
    private List<BusinessFilter> filters;
    private int currentPosition = 0;

    public DefaultBusinessFilterChain(BusinessFilterConfig config) {
        this.filterConfig=config;
        init();
    }

    public DefaultBusinessFilterChain(List<BusinessFilter> filters) {
        this.filters = filters;
    }

    public void init(){
        if(filterConfig!=null&& !CollectionUtils.isEmpty(filterConfig.getFilters())){
            this.filters=new ArrayList<>(filterConfig.getFilters());
        }
    }

    @Override
    public void doBusinessFilter(Message message, WebSocketSession session) throws IOException{
        if(filters==null||this.currentPosition>=filters.size()){
            return;
        }else {
            ++this.currentPosition;
            filters.get(currentPosition-1).doBusinessFilter(message,session,this);
        }
    }

    public BusinessFilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig(BusinessFilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public List<BusinessFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<BusinessFilter> filters) {
        this.filters = filters;
    }
}
