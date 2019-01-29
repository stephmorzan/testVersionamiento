package com.testing.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.testing.testflow.TFVerTramite;
import com.testing.tool.cte.TestCaseCte;

public class TCVerTramite {

	TFVerTramite tester;

	public TCVerTramite() {
		tester = new TFVerTramite();
	}

	@Test
	public void testBuscarUltimoTramite() {
		String buscarUltimoTramite = tester.buscarUltimoTramite();
		boolean contains = buscarUltimoTramite.contains(TestCaseCte.REQUISITO);
		assertEquals(true, contains);
	}

	@Test
	public void testBuscarTramite() {
		boolean contains = tester.seleccionarTramite(/* 241 */).contains(TestCaseCte.REQUISITO);
		assertEquals(true, contains);
	}

}
