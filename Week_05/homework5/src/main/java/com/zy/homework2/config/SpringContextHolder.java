package com.zy.homework2.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext = null;

    //private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }
    /**
     * 从静态变量applicationContext中取得所有接口实现类.
     */
    public static <T> List<T> getBeanListOfType(Class<T> type){
        List<T> list = new ArrayList<T>();
        Map<String, T> result =applicationContext.getBeansOfType(type);
        for(Map.Entry<String, T> entry:result.entrySet()){
            list.add(entry.getValue());
        }
        return list;
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        //logger.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        //logger.debug("注入ApplicationContext到SpringContextHolder:{}", applicationContext);

        if (SpringContextHolder.applicationContext != null) {
            //logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
            //        + SpringContextHolder.applicationContext);
        }

        SpringContextHolder.applicationContext = applicationContext; //NOSONAR
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

}
