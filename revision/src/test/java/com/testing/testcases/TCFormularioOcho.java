package com.testing.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.testing.testflow.TFFormularioOcho;

public class TCFormularioOcho {

	TFFormularioOcho tester = new TFFormularioOcho();

	public TCFormularioOcho() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testLlenarFormulario() {
		boolean llenarFormularioOcho = tester.llenarFormularioOcho();
		assertTrue("Un error en el metodo de TestFormularioOcho", llenarFormularioOcho);
	}
}
