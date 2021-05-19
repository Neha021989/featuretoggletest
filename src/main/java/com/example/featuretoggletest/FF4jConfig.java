package com.example.featuretoggletest;

import org.ff4j.FF4j;
import org.ff4j.audit.repository.InMemoryEventRepository;
import org.ff4j.core.Feature;
import org.ff4j.property.store.InMemoryPropertyStore;
import org.ff4j.store.InMemoryFeatureStore;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class FF4jConfig {
	private static final String ACCOUNT_DATA = "account";

	@Bean
	public FF4j getFF4j() {
		FF4j ff4j = new FF4j();

		ff4j.setFeatureStore(new InMemoryFeatureStore());
		ff4j.setPropertiesStore(new InMemoryPropertyStore());
		ff4j.setEventRepository(new InMemoryEventRepository());
		ff4j.audit(true);
		ff4j.autoCreate(true);
		if (!ff4j.exist(ACCOUNT_DATA)) {
			ff4j.createFeature(new Feature(ACCOUNT_DATA, true));
		}
		return ff4j;
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet dispatcherServlet = new CustomDispatcherServlet();
		dispatcherServlet.setThreadContextInheritable(true);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		return dispatcherServlet;
	}

	@Bean
	public DispatcherServletRegistrationBean dispatcherServletRegistration() {

		DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(dispatcherServlet(),
				"/");
		registration.setLoadOnStartup(0);
		registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
		return registration;
	}
}
