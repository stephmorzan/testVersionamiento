package com.testing.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.testing.testflow.TFClonarRequisito;

public class TCClonarRequisito {
	
	TFClonarRequisito tester = new TFClonarRequisito();

	public TCClonarRequisito() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testMapearReqs() {
		boolean clonarRequisitos = tester.clonarRequisitos();
		assertTrue("No es null, se cayó el método.", clonarRequisitos);
	}

}
