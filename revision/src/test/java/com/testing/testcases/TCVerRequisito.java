package com.testing.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.testing.testflow.TFVerRequisito;
import com.testing.tool.cte.TestCaseCte;

public class TCVerRequisito {
	
	TFVerRequisito tester = new TFVerRequisito();

	public TCVerRequisito() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void testBuscarUltimoRequisito() {
		String buscarEditarUltimoRequisito = tester.buscarEditarUltimoRequisito();
		boolean contains = buscarEditarUltimoRequisito.contains(TestCaseCte.MODIFICAR_FORMULARIO);
		assertEquals(true, contains);
	}
	
	@Test
	public void testSeleccionarRequisito(){
		boolean contains = tester.seleccionarEditarRequisito().contains(TestCaseCte.MODIFICAR_FORMULARIO);
		assertEquals(true, contains);
	}
	
}
