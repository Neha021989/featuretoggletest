package com.example.featuretoggletest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class CustomDispatcherServlet extends DispatcherServlet {

	@Autowired
	public FeatureCheck featureCheck;

	@Autowired
	SpringUtil springUtil;

	CustomDispatcherServlet() {
		super();
	}

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!featureCheck.accountEnabled()) {
			springUtil.removeExistingBean("accountController");
		} else {
			springUtil.addNewBean("accountController");
		}
		super.doDispatch(request, response);

	}

}
