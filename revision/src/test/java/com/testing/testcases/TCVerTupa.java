package com.testing.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.testing.testflow.TFVerTupa;
import com.testing.tool.cte.TestCaseCte;

public class TCVerTupa {

	TFVerTupa tester = new TFVerTupa();
	
	public TCVerTupa() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void testBuscarUltimoTupa() {
		String buscarUltimoTupa = tester.buscarUltimoTupa();
		boolean contains = buscarUltimoTupa.contains(TestCaseCte.TRAMITE);
		assertEquals(true, contains);
	}
	
	@Test
	public void testBuscarTupa() {
		String seleccionarTupa = tester.seleccionarTupa();
		boolean contains = seleccionarTupa.contains(TestCaseCte.TRAMITE);
		assertEquals(true, contains);
	}
}
