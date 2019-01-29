package com.testing.testcases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.testing.dataProvider.ConfigFileReader;
import com.testing.testflow.TFFormulario;
import com.testing.tool.DataTool;
import com.testing.tool.ExtraTool;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.RegExpCte;

/** FIXME STE uso de Inspeccion Class para acceso a nombre de metodos */
public class TCModificarEtiqueta {

	private static final String HOLA1234 = "Hola1234";

	TFFormulario tester = new TFFormulario();
	ExtraTool soporte = new ExtraTool();
	ConfigFileReader config = new ConfigFileReader();
	// FakeValuesService service = new FakeValuesService(new
	// Locale(BaseCte.BS_LOCALE_ES), new RandomService());

	public TCModificarEtiqueta() {
		// TODO Auto-generated constructor stub

	}

	// *************************************************************Estas son
	// etiquetas de tipo
	// Texto**********************************************************************

	// @Test
	public void agregarMultiplesEtiquetas() {
		System.out.println("****************agregarMultiplesEtiquetas**********************************");

		for (int i = 0; i < 10; i++) {
			String regexInputCharacters = config.getRegexInputCharacters();
			// service.regexify(regexInputCharacters);
			String regexify = DataTool.generar(regexInputCharacters);
			boolean agregarEtiqueta = tester.agregarEtiqueta(regexify);
			assertTrue("No se ha agregado columna " + i, agregarEtiqueta);
		}
	}

	@Test
	public void agregarEtiquetaConNuevaSesion() {
		boolean verificarEtiquetaAgregadaConNuevaSesion = tester.verificarEtiquetaAgregadaConNuevaSesion(HOLA1234);
		assertTrue("No se esta ejecutando bien ", verificarEtiquetaAgregadaConNuevaSesion);
	}

	// @Test
	public void etiquetaTildesEgne() {
		System.out.println("****************etiquetaTildesEnghe**********************************");
		String regexInputCharacters = config.getRegexInputCharacters();
		// String regexify = service.regexify(regexInputCharacters);
		String regexify = DataTool.generar(regexInputCharacters);
		boolean agregarEtiqueta = tester.agregarEtiqueta(regexify);
		assertTrue("No se ha agregado columna especial", agregarEtiqueta);
	}

	// @Test
	public void etiquetaMuchosCaracteres() {
		System.out.println("****************etiquetaMilCaracteres**********************************");
		int regexQuantifyInputCharacters = config.getRegexQuantifyInputCharacters();
		String regex = config.getRegexInputCharacters() + BaseCte.BS_TXT_LLAVE_IZQ + regexQuantifyInputCharacters + BaseCte.BS_TXT_LLAVE_DER;
		// String regexify = service.regexify(regex);
		String regexify = DataTool.generar(regex);
		boolean agregarEtiqueta = tester.agregarEtiqueta(regexify);
		assertFalse("Se ha agregado columna con mil caracteres", agregarEtiqueta);
	}

	// @Test
	public void etiquetaVacia() {
		System.out.println("****************etiquetaVacia**********************************");
		boolean agregarEtiqueta = tester.agregarEtiqueta(BaseCte.BS_TXT_VACIO);
		assertFalse("Se ha agregado columna con mil caracteres", agregarEtiqueta);
	}

	// @Test
	public void etiquetaEspacioEnBlanco() {
		System.out.println("****************etiquetaEspacioEnBlanco**********************************");
		boolean agregarEtiqueta = tester.agregarEtiqueta(BaseCte.BS_TXT_ESPACIO_BLANCO);
		assertFalse("Se ha agregado columna con espacio en blanco", agregarEtiqueta);
	}

	// @Test
	public void etiquetaSoloNumeros() {
		System.out.println("****************etiquetaSoloNumeros**********************************");
		boolean agregarEtiqueta = tester.agregarEtiqueta(BaseCte.BS_TXT_1234567890);
		assertFalse("Se ha agregado columna con solo numeros", agregarEtiqueta);
	}

	// la etiqueta solo tiene signos de puntuacion, como puntos, comas, exclamacion
	// e interrogacion
	// @Test
	public void etiquetaSignosPuntuacion() {
		System.out.println("****************etiquetaSignosPuntuacion**********************************");
		boolean agregarEtiqueta = tester.agregarEtiqueta(BaseCte.BS_TXT_SIGNOS2);
		assertFalse("Se ha agregado columna con signos de puntuacion", agregarEtiqueta);
	}

	// la etiqueta tiene el resto de elementos
	// @Test
	public void etiquetaSimbolosEspeciales() {
		System.out.println("****************etiquetaSimbolosEspeciales**********************************");
		boolean agregarEtiqueta = tester.agregarEtiqueta(RegExpCte.RGX_SIGNOS);
		assertFalse("Se ha agregado columna con simbolos especiales", agregarEtiqueta);
	}

	// *************************************************************Estas son
	// etiquetas de tipo
	// Combo**********************************************************************

	// @Test
	public void testGuardarEtiquetaCompleja1() {
		String regexInputCharacters = config.getRegexInputCharacters();
		boolean agregarEtiquetaComplejando1 = tester.agregarEtiquetaComplejando1(regexInputCharacters);
		assertTrue("Estoy en agregarEtiquetaCompleja1()", agregarEtiquetaComplejando1);
	}

	// @Test
	public void testGuardarEtiquetaCombos2() {
		int clicksAgregar;
		int clicksEliminar;
		do {
			ExtraTool metodosSoporte = new ExtraTool();
			clicksAgregar = metodosSoporte.cantValoresCombo();
			ExtraTool metodosSoporte2 = new ExtraTool();
			clicksEliminar = metodosSoporte2.cantValoresCombo();
		} while (clicksAgregar < clicksEliminar);
		System.out.println("**************** Más clicks en Eliminar Etiqueta que Agregar Etiqueta **********************************");
		String regexInputCharacters = config.getRegexInputCharacters();
		boolean agregarEtiquetaComplejando2 = tester.agregarEtiquetaComplejando2(regexInputCharacters, clicksAgregar, clicksEliminar);
		assertTrue("Estoy en agregarEtiquetaCompleja2()", agregarEtiquetaComplejando2);
	}

	// @Test
	public void testGuardarEtiquetaCombos3() {
		int clicksAgregar;
		int clicksEliminar;
		do {
			/** FIXME STE probable tool static */
			ExtraTool metodosSoporte = new ExtraTool();
			clicksAgregar = metodosSoporte.cantValoresCombo();
			clicksEliminar = metodosSoporte.cantValoresCombo();
		} while (clicksAgregar == clicksEliminar);
		System.out.println("**************** Igual clicks en Eliminar Etiqueta que Agregar Etiqueta **********************************");
		String regexInputCharacters = config.getRegexInputCharacters();
		boolean agregarEtiquetaComplejando2 = tester.agregarEtiquetaComplejando2(regexInputCharacters, clicksAgregar, clicksEliminar);
		assertTrue("Estoy en agregarEtiquetaCompleja3()", agregarEtiquetaComplejando2);
	}

	// @Test
	public void testGuardarEtiquetaCombos4() {
		int clicksAgregar;
		int clicksEliminar;
		do {
			ExtraTool metodosSoporte = new ExtraTool();
			clicksAgregar = metodosSoporte.cantValoresCombo();
			clicksEliminar = metodosSoporte.cantValoresCombo();
		} while (clicksAgregar > clicksEliminar);
		System.out.println("**************** Menos clicks en Eliminar Etiqueta que Agregar Etiqueta **********************************");
		String regexInputCharacters = config.getRegexInputCharacters();
		boolean agregarEtiquetaComplejando2 = tester.agregarEtiquetaComplejando2(regexInputCharacters, clicksAgregar, clicksEliminar);
		assertTrue("Estoy en agregarEtiquetaCompleja4()", agregarEtiquetaComplejando2);
	}

}
