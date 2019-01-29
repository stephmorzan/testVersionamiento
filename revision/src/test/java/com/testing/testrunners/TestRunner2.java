package com.testing.testrunners;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.testing.testcases.TCModificarEtiqueta;

public class TestRunner2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		TestVerRequisito test1 = new TestVerRequisito();
		test1.buscarEditarUltimoRequisito();
		*/
		
		Result result = JUnitCore.runClasses(TCModificarEtiqueta.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
			
		}
			
		
	}

}
