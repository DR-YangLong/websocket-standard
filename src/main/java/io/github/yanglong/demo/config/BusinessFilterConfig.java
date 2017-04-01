package io.github.yanglong.demo.config;

import io.github.yanglong.demo.filter.BusinessFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * package: io.github.yanglong.demo.config <br/>
 * functional describe:
 *
 * @author DR.YangLong [410357434@163.com]
 * @version 1.0    2017/3/29
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "filters")
@Configuration
public class BusinessFilterConfig {
    private static Logger logger= LoggerFactory.getLogger(BusinessFilterConfig.class);
    private Class[] filterClass;
    private ArrayList<BusinessFilter> filters=new ArrayList<>();

    @PostConstruct
    private void init(){
        if(filterClass!=null&&filterClass.length>0){
            for (Class clazz:filterClass){
                try {
                    if(Arrays.asList(clazz.getInterfaces()).contains(BusinessFilter.class)) {
                        filters.add((BusinessFilter) clazz.newInstance());
                    }
                } catch (InstantiationException |IllegalAccessException e) {
                    logger.error("BusinessFilterConfig init error!",e);
                }
            }
        }
    }

    public Class[] getFilterClass() {
        return filterClass;
    }

    public void setFilterClass(Class[] filterClass) {
        this.filterClass = filterClass;
    }

    public ArrayList<BusinessFilter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<BusinessFilter> filters) {
        this.filters = filters;
    }
}
