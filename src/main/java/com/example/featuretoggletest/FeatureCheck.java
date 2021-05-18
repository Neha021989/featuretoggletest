package com.example.featuretoggletest;

import org.ff4j.FF4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeatureCheck {
	@Autowired
	public FF4j ff4j;

	public boolean accountEnabled() {
		if (ff4j.check("account")) {
			return true;
		} else {
			return false;
		}

	}

}
