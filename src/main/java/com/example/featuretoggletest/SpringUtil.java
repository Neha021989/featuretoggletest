package com.example.featuretoggletest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

	public static <T> T getBean(String beanId, Class<T> beanClass) {
		return applicationContext.getBean(beanId, beanClass);
	}

	public void removeExistingBean(String beanId) {

		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
		registry.removeBeanDefinition(beanId);
	}

	public void addNewBean(String beanId) {

		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(AccountController.class)
				.setLazyInit(true);
		if (!registry.containsBeanDefinition(beanId)) {
			registry.registerBeanDefinition(beanId, builder.getBeanDefinition());
		}

	}
}
