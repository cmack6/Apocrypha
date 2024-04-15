package edu.ycp.cs320.lab02.controller;

import edu.ycp.cs320.lab02.model.Library;

public class LoginController {
	private Library model = null;
	
	public LoginController(Library model) {
		this.model = model;
	}
	
	public boolean checkUserName(String name) {
		return model.validateUserName(name);
	}
	
	public boolean validateCredentials(String name, String pw) {
		return model.validatePW(name, pw);
	}
}
