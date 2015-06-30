/**
 * 
 */
package com.wesimplify.nodabba.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author sdoddi
 * This class will help other services to return the services and integration objects
 */
public class BusinessAwareService implements ApplicationContextAware {

	private final static Logger logger = LoggerFactory.getLogger(BusinessAwareService.class);

	private ApplicationContext applicationContext;
	
	/**
	 * Spring framework will set this object during initialization
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 *  returns the bean
	 * @param beanId
	 * @return <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanId) {
		try {
			return (T) applicationContext.getBean(beanId);
		}
		catch (Exception ex) {
			logger.error("Error when getting the bean for {}", beanId, ex);
		}
		throw new RuntimeException("Error getting bean from BusinessAwareService for "+beanId);
	}
	
	/**
	 * Returns the beans for specific bean id and class type. if not found, this method will throw runtimeexception
	 * @param beanId
	 * @return <T>
	 */
	public <T> T getBean(String beanId, Class<T> name) {
		try {
			return applicationContext.getBean(beanId, name);
		}
		catch (Exception ex) {
			logger.error("Error when getting the bean for {}", beanId, ex);
		}
		throw new RuntimeException("Error getting bean from BusinessAwareService for "+beanId);
	}
	
}
