package com.testing.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.testing.dataProvider.ConfigFileReader;
import com.testing.testflow.TFFormulario;
import com.testing.tool.DataTool;
import com.testing.tool.ExtraTool;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.TestCaseCte;

public class TCGuardarFormulario {

	private static final String RG_AA = "+/*-@#$%&()={[]}<>";
	private static final String RG_AB = "!?'¿¡;,.:";
	private static final String RG_ABB = "áÉíÓüÜêÂÔñÑ";

	TFFormulario tester = new TFFormulario();
	ExtraTool soporte = new ExtraTool();

	ConfigFileReader config = new ConfigFileReader();
	/** FIXME STE probable tool static */
	//FakeValuesService service = new FakeValuesService(new Locale(BaseCte.BS_LOCALE_ES), new RandomService());
	/** FIXME STE probable tool static */
	// final String regex = service.regexify(config.getRegexInputCharacters() + "{"
	// + config.getRegexQuantifyInputCharacters() + "}");
	final String regex = DataTool.generar(config.getRegexInputCharacters() + "{" + config.getRegexQuantifyInputCharacters() + "}");

	public TCGuardarFormulario() {
		// TODO Auto-generated constructor stub
	}

	// método para validar si este método puede ejecutar un Agregar Etiqueta.
	@Test
	public void testGuardarEtiqueta() {
		System.out.println("**************** testGuardarEtiqueta() Simple **********************************");
		boolean agregarEtiqueta = tester.agregarEtiqueta(regex);
		assertTrue("Estoy en agregarEtiqueta()", agregarEtiqueta);
	}

	// @Test
	public void testModDescripFormTildesEnghe() {
		System.out.println("**************** testModDescripFormTildesEnghe() **********************************");
		String modificarDescripFormulario = tester.modificarDescripFormulario(RG_ABB);
		boolean contains = modificarDescripFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Descripcion con Tildes y enghe", contains);
	}

	// @Test
	public void testModCodFormTildesEnghe() {
		System.out.println("**************** testModCodFormTildesEnghe() **********************************");
		String modificarCodigoFormulario = tester.modificarCodigoFormulario(RG_ABB);
		boolean contains = modificarCodigoFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Codigo de Formulario con Tildes y enghe", contains);
	}

	@Test
	public void testModFormTildesEnghe() {
		System.out.println("**************** testModFormTildesEnghe() **********************************");
		String modificarFormulario = tester.modificarFormulario(regex, regex);
		boolean contains = modificarFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Formulario con Tildes y enghe", contains);
	}

	// @Test
	public void testModDescripFormEspaciosEnBlanco() {
		String modificarDescripFormulario = tester.modificarDescripFormulario(BaseCte.BS_TXT_VACIO_3);
		boolean contains = modificarDescripFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Descripcion con Espacios En Blanco", contains);
	}

	// @Test
	public void testModCodFormEspaciosEnBlanco() {
		String modificarCodigoFormulario = tester.modificarCodigoFormulario(BaseCte.BS_TXT_VACIO_2);
		boolean contains = modificarCodigoFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Codigo de Formulario con Espacios En Blanco", contains);
	}

	@Test
	public void testModFormEspaciosEnBlanco() {
		String modificarFormulario = tester.modificarFormulario(BaseCte.BS_TXT_VACIO_2, BaseCte.BS_TXT_VACIO_1);
		boolean contains = modificarFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Formulario con Tildes y enghe", contains);
	}

	public void testModDescripFormMuchosCaracteres() {
		String generarMuchosCaracteres = soporte.generarMuchosCaracteres();
		String modificarDescripFormulario = tester.modificarDescripFormulario(generarMuchosCaracteres);
		boolean contains = modificarDescripFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Descripcion con 230 caracteres", contains);
	}

	public void testModCodFormMuchosCaracteres() {
		String generarMuchosCaracteres = soporte.generarMuchosCaracteres();
		String modificarCodigoFormulario = tester.modificarCodigoFormulario(generarMuchosCaracteres);
		boolean contains = modificarCodigoFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Codigo de Formulario con 230 caracteres", contains);
	}

	public void testModFormMuchosCaracteres() {
		String generarMuchosCaracteres = soporte.generarMuchosCaracteres();
		String modificarFormulario = tester.modificarFormulario(generarMuchosCaracteres, generarMuchosCaracteres);
		boolean contains = modificarFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Formulario con 230 caracteres", contains);
	}

	public void testModDescripFormSignosPuntuacion() {
		String modificarDescripFormulario = tester.modificarDescripFormulario(RG_AB);
		boolean contains = modificarDescripFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Descripcion de Formulario con Signos de Puntuación", contains);
	}

	public void testModCodFormSignosPuntuacion() {
		String modificarCodigoFormulario = tester.modificarCodigoFormulario(RG_AB);
		boolean contains = modificarCodigoFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Codigo de Formulario con Signos de Puntuación", contains);
	}

	public void testModFormSignosPuntuacion() {
		String modificarFormulario = tester.modificarFormulario(RG_AB, RG_AB);
		boolean contains = modificarFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Formulario con Signos de Puntuación", contains);
	}

	public void testModDescripFormSimbolosEspeciales() {
		String modificarDescripFormulario = tester.modificarDescripFormulario(RG_AA);
		boolean contains = modificarDescripFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Descripcion de Formulario con Símbolos Especiales", contains);
	}

	public void testModCodFormSimbolosEspeciales() {
		String modificarCodigoFormulario = tester.modificarCodigoFormulario(RG_AA);
		boolean contains = modificarCodigoFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Codigo de Formulario con Símbolos Especiales", contains);
	}

	public void testModFormSimbolosEspeciales() {
		String modificarFormulario = tester.modificarFormulario(RG_AA, RG_AA);
		boolean contains = modificarFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Formulario con Símbolos Especiales", contains);
	}

	public void testModDescripFormVacio() {
		String modificarDescripFormulario = tester.modificarDescripFormulario(BaseCte.BS_TXT_VACIO);
		boolean contains = modificarDescripFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Descripcion de Formulario con campo vacío", contains);
	}

	public void testModCodFormVacio() {
		String modificarCodigoFormulario = tester.modificarCodigoFormulario(BaseCte.BS_TXT_VACIO);
		boolean contains = modificarCodigoFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Codigo de Formulario con campo vacío", contains);
	}

	public void testModFormVacio() {
		String modificarFormulario = tester.modificarFormulario(BaseCte.BS_TXT_VACIO, BaseCte.BS_TXT_VACIO);
		boolean contains = modificarFormulario.contains(TestCaseCte.REQUISITO);
		assertTrue("No se agrego Formulario con campo vacío", contains);
	}
}
