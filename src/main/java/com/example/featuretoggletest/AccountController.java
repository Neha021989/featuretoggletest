package com.example.featuretoggletest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	public FeatureCheck featureCheck;

	@GetMapping
	public String accounts() {
		if (!featureCheck.accountEnabled()) {
			throw new UnsupportedOperationException();
		} else {
			return "Here is your account";
		}
	}

}
