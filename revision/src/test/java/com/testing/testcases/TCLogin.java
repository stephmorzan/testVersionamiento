package com.testing.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.testing.testflow.TFLogin;
import com.testing.tool.cte.TestCaseCte;

public class TCLogin {

	private static final String LOGIN_INCORRECTO = "Login incorrecto";
	TFLogin tester = new TFLogin();

	public TCLogin() {
		
	}

	@Test
	public void testLogin() {
		boolean sePodraLogin = tester.sePodraLogin(TestCaseCte.ASD, TestCaseCte.ASD);
		assertEquals(LOGIN_INCORRECTO, false, sePodraLogin);
	}
}
