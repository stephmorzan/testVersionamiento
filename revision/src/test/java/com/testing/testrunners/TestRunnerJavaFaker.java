package com.testing.testrunners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.testing.testcases.TCLogin;

public class TestRunnerJavaFaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Result result = JUnitCore.runClasses(TCLogin.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
			
		}
			
		
	}

}
