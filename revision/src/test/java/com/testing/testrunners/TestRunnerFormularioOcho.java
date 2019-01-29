package com.testing.testrunners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.testing.testcases.TCFormularioOcho;

public class TestRunnerFormularioOcho {

	public TestRunnerFormularioOcho() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Result result = JUnitCore.runClasses(TCFormularioOcho.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
			
		}
		
	}

}
