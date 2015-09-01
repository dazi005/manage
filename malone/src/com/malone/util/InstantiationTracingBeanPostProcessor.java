/**
 * 
 */
package com.malone.util;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author pengl
 * 打印spring在启动时初始化的bean name
 * 2015-6-8上午11:41:35
 */
//@Component
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {
	@Resource(name="dateUtil")
	private DateUtil dateUtil;
	private Logger _logger = LogManager.getLogger();
	
	//在创建bean前
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    //在创建bean后输出bean的信息
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    	_logger.warn("Bean '" + beanName + "' created , Time: " + dateUtil.toLongDate());
        return bean;
    }
}
