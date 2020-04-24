package com.lyt.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    /**
     * @Description: 获取代理的bean实例
     * @Param: [clz]
     * @return: T
     * @Author: lyt
     * @Date: 2020/3/25
     */
    public static <T> T getBean(Class<?> clz) {
        String clzName = clz.getName();
        String name = clzName.substring(clzName.lastIndexOf('.') + 1);
        String beanName = (name.charAt(0) + "").toLowerCase() + name.substring(1);

        return (T) getBean(beanName, clz);
    }
}