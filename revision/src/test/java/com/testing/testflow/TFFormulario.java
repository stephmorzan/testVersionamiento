package com.testing.testflow;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.testing.dataProvider.ConfigFileReader;
import com.testing.tool.DataTool;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.ByCte;
import com.testing.tool.cte.TimeCte;

public class TFFormulario {

	WebDriver driver;
	Logger miLogger;
	TFVerRequisito requisito = new TFVerRequisito();
	ConfigFileReader config;
	int clicksAgregarComboVal;
	String randomCadenaComboVal;

	public TFFormulario() {
		// TODO Auto-generated constructor stub
		config = new ConfigFileReader();
		this.driver = requisito.getDriver();
		this.miLogger = requisito.getMiLogger();
		setUp();
	}

	public void setUp() {
		// requisito.buscarEditarUltimoRequisito();
		requisito.seleccionarEditarRequisito();
	}

	// no se cambia de URL, pero sí aumenta de columnas
	public boolean agregarEtiqueta(String etiqueta) {
		try {
			Options manage = driver.manage();
			Timeouts timeouts = manage.timeouts();
			timeouts.implicitlyWait(TimeCte._1200000000, TimeUnit.SECONDS);
			// WebElement nombreEtiqueta = driver.findElement(By.id("nombreEtiqueta"));
			By xpath = By.xpath(ByCte.BY_XPATH_NOMBRE_ETIQUETA);
			WebElement nombreEtiqueta = driver.findElement(xpath);
			nombreEtiqueta.clear();
			nombreEtiqueta.sendKeys(etiqueta);
			By xpath2 = By.xpath(ByCte.BY_XPATH_TIPO_ETIQUETA);
			WebElement findElement = driver.findElement(xpath2);
			Select tipoEtiqueta = new Select(findElement);
			Random random = new Random();
			int valorTipo = random.nextInt(2) + 1;
			String value = valorTipo + BaseCte.BS_TXT_VACIO;
			tipoEtiqueta.selectByValue(value);
			WebElement firstSelectedOption = tipoEtiqueta.getFirstSelectedOption();
			String text = firstSelectedOption.getText();
			String msg = "Valor del tipo = " + text;
			miLogger.log(Level.INFO, msg);

			switch (valorTipo) {
			case 1:
				System.out.println("Valor del tipo: texto");
				/*
				 * 
				 * if(usuario.getTipo == "admin"){
				 * 
				 * int tendraValorEtiqueta = random.nextInt(2); if (tendraValorEtiqueta==1) {
				 * System.out.println("Entro al if de si tendra valor etiqueta o no");
				 * WebElement valorEtiqueta = driver.findElement(By.id("valorEtiqueta0")); }
				 * 
				 * }
				 */

				break;
			case 2:
				System.out.println("Valor del tipo: combo");
				clicksAgregarComboVal = random.nextInt(10); // esto se comentara
				System.out.println("Cantidad de valores para el combobox = " + clicksAgregarComboVal);

				for (int k = 0; k < clicksAgregarComboVal; k++) {
					By id = By.id(ByCte.BY_ID_BTN_AGREGAR_CAMPO);
					WebElement findElementById = driver.findElement(id);
					findElementById.click();
				}

				for (int k = 0; k < clicksAgregarComboVal + 1; k++) {
					String id = ByCte.BY_ID_VALOR_ETIQUETA + k;
					By id2 = By.id(id);
					WebElement findElementById2 = driver.findElement(id2);
					findElementById2.clear();
					WebElement findElementdd2 = driver.findElement(id2);
					String regexInputCharacters = config.getRegexInputCharacters();
					// Locale locale = new Locale("es");
					// RandomService randomService = new RandomService();
					// FakeValuesService fakeValuesService = new FakeValuesService(locale,
					// randomService);
					// String regexify = fakeValuesService.regexify(regexInputCharacters);
					String regexify = DataTool.generar(regexInputCharacters);
					findElementdd2.sendKeys(regexify + k);
					String id3 = ByCte.BY_ID_ORDEN_ETIQUETA + k;
					By id4 = By.id(id3);
					WebElement findElement2 = driver.findElement(id4);
					findElement2.clear();
					int aleatorioOrdenComboBox = config.getAleatorioOrdenComboBox();
					int nextInt = random.nextInt(aleatorioOrdenComboBox);
					findElement2.sendKeys(nextInt + BaseCte.BS_TXT_VACIO);
					System.out.println("Valor del orden en el combo" + nextInt);
				}
				break;
			}

			boolean seleccionaCbk1 = random.nextBoolean();
			boolean seleccionaCbk2 = random.nextBoolean();
			String x = "Valores de: " + "\nSeleccionarCbk1 = " + seleccionaCbk1 + "\nSeleccionarCbk2 = " + seleccionaCbk2;
			System.out.println(x);

			if (seleccionaCbk1 == true) {
				By id = By.id(ByCte.BY_ID_CBK1);
				WebElement cbkObligatorio = driver.findElement(id);
				cbkObligatorio.click();
				boolean selected = cbkObligatorio.isSelected();
				miLogger.log(Level.INFO, "Requisito requerido chequeado = " + selected);
			}

			if (seleccionaCbk2 == true) {
				By id = By.id(ByCte.BY_ID_CBK2);
				WebElement cbkLongTexto = driver.findElement(id);
				cbkLongTexto.click();
				timeouts.implicitlyWait(TimeCte._1800000000, TimeUnit.SECONDS);
				By id2 = By.id(ByCte.BY_ID_TAMANIO_TEXTO);
				WebElement longTexto = driver.findElement(id2);
				int valorLongTextoReq = config.getValorLongTextoReq();
				longTexto.sendKeys(valorLongTextoReq + BaseCte.BS_TXT_VACIO);
			}

			By id = By.id(ByCte.BY_ID_BTN_GUARDAR_ETIQUETA);
			WebElement findElement2 = driver.findElement(id);
			findElement2.click();

			if (!saleAlerta()) {
				miLogger.log(Level.INFO, "Se ha guardado etiqueta con nombre " + etiqueta);
				return verificarEtiquetaAgregada(etiqueta);
			} else {
				miLogger.log(Level.INFO, "NO se ha guardado etiqueta con nombre " + etiqueta);
				TargetLocator switchTo = driver.switchTo();
				Alert alert = switchTo.alert();
				alert.accept();
				switchTo.defaultContent();
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	// no se cambia de URL, pero sí aumenta de columnas
	public boolean agregarEtiquetaComplejando1(String etiqueta) {
		try {
			Options manage = driver.manage();
			Timeouts timeouts = manage.timeouts();
			timeouts.implicitlyWait(TimeCte._1200000000, TimeUnit.SECONDS);
			// WebElement nombreEtiqueta = driver.findElement(By.id("nombreEtiqueta"));
			By xpath = By.xpath(ByCte.BY_XPATH_NOMBRE_ETIQUETA);
			WebElement nombreEtiqueta = driver.findElement(xpath);
			// nombreEtiqueta.clear();
			nombreEtiqueta.sendKeys(etiqueta);
			By xpath2 = By.xpath(ByCte.BY_XPATH_TIPO_ETIQUETA);
			WebElement findElement = driver.findElement(xpath2);
			Select tipoEtiqueta = new Select(findElement);
			Random random = new Random();
			tipoEtiqueta.selectByValue(2 + BaseCte.BS_TXT_VACIO);
			WebElement firstSelectedOption = tipoEtiqueta.getFirstSelectedOption();
			String text = firstSelectedOption.getText();
			miLogger.log(Level.INFO, "Valor del tipo = " + text);
			clicksAgregarComboVal = random.nextInt(10); // esto se comentara
			System.out.println("Cantidad de valores para el combo = " + clicksAgregarComboVal);

			for (int k = 0; k < clicksAgregarComboVal; k++) {
				By id = By.id(ByCte.BY_ID_BTN_AGREGAR_CAMPO);
				WebElement findElement2 = driver.findElement(id);
				findElement2.click();
			}

			for (int k = 0; k < clicksAgregarComboVal + 1; k++) {
				String id = ByCte.BY_ID_VALOR_ETIQUETA + k;
				By id2 = By.id(id);
				String regexInputCharacters = config.getRegexInputCharacters();
				WebElement findElementBy2 = driver.findElement(id2);
				findElementBy2.sendKeys(regexInputCharacters + k);
				String id3 = ByCte.BY_ID_ORDEN_ETIQUETA + k;
				By id4 = By.id(id3);
				WebElement findElement2 = driver.findElement(id4);
				int aleatorioOrdenComboBox = config.getAleatorioOrdenComboBox();
				int nextInt = random.nextInt(aleatorioOrdenComboBox);
				findElement2.sendKeys(nextInt + BaseCte.BS_TXT_VACIO);
			}
			tipoEtiqueta.selectByValue(1 + BaseCte.BS_TXT_VACIO);

			By id = By.id(ByCte.BY_ID_BTN_GUARDAR_ETIQUETA);
			WebElement findElement2 = driver.findElement(id);
			findElement2.click();

			if (!saleAlerta()) {
				miLogger.log(Level.INFO, "Se ha guardado etiqueta con nombre " + etiqueta);
				return verificarEtiquetaAgregada(etiqueta);
			} else {
				miLogger.log(Level.INFO, "NO se ha guardado etiqueta con nombre " + etiqueta);
				TargetLocator switchTo = driver.switchTo();
				Alert alert = switchTo.alert();
				alert.accept();
				switchTo.defaultContent();
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean agregarEtiquetaComplejando2(String etiqueta, int clicksAgregarComboVal, int clicksEliminarComboVal) {
		try {
			Options manage = driver.manage();
			Timeouts timeouts = manage.timeouts();
			timeouts.implicitlyWait(TimeCte._1200000000, TimeUnit.SECONDS);
			// WebElement nombreEtiqueta = driver.findElement(By.id("nombreEtiqueta"));
			By xpath = By.xpath(ByCte.BY_XPATH_NOMBRE_ETIQUETA);
			WebElement nombreEtiqueta = driver.findElement(xpath);
			nombreEtiqueta.sendKeys(etiqueta);
			By xpath2 = By.xpath(ByCte.BY_XPATH_TIPO_ETIQUETA);
			WebElement findElement = driver.findElement(xpath2);
			Select tipoEtiqueta = new Select(findElement);
			Random random = new Random();
			tipoEtiqueta.selectByValue(2 + BaseCte.BS_TXT_VACIO);
			WebElement firstSelectedOption = tipoEtiqueta.getFirstSelectedOption();
			String text = firstSelectedOption.getText();
			miLogger.log(Level.INFO, "Valor del tipo = " + text);
			clicksAgregarComboVal = random.nextInt(10); // esto se comentara
			System.out.println("Cantidad de valores para el combo = " + clicksAgregarComboVal);

			for (int k = 0; k < clicksAgregarComboVal; k++) {
				By id = By.id(ByCte.BY_ID_BTN_AGREGAR_CAMPO);
				WebElement findElement2 = driver.findElement(id);
				findElement2.click();
			}

			for (int k = 0; k < clicksAgregarComboVal; k++) {
				// acá debe haber un getValorCombo
				String id = ByCte.BY_ID_VALOR_ETIQUETA + k;
				By byid2 = By.id(id);
				WebElement findElement2 = driver.findElement(byid2);
				findElement2.clear();
				String string2 = config.getRegexInputCharacters() + k;
				findElement2.sendKeys(string2);
				String id2 = ByCte.BY_ID_ORDEN_ETIQUETA + k;
				By id3 = By.id(id2);
				int aleatorioOrdenComboBox = config.getAleatorioOrdenComboBox();
				WebElement findElement3 = driver.findElement(id3);
				String string3 = random.nextInt(aleatorioOrdenComboBox) + BaseCte.BS_TXT_VACIO;
				findElement3.sendKeys(string3);
			}

			for (int k = 0; k < clicksEliminarComboVal + 1; k++) {
				By id = By.id(ByCte.BY_ID_BTN_RETIRAR_CAMPO);
				WebElement findElement2 = driver.findElement(id);
				findElement2.click();
			}

			boolean seleccionaCbk1 = random.nextBoolean();
			boolean seleccionaCbk2 = random.nextBoolean();
			String x = "Valores de: " + "\nSeleccionarCbk1 = " + seleccionaCbk1 + "\nSeleccionarCbk2 = " + seleccionaCbk2;
			System.out.println(x);

			if (seleccionaCbk1 == true) {
				By id = By.id(ByCte.BY_ID_CBK1);
				WebElement cbkObligatorio = driver.findElement(id);
				cbkObligatorio.click();
				boolean selected = cbkObligatorio.isSelected();
				miLogger.log(Level.INFO, "Requisito requerido chequeado = " + selected);
			}

			if (seleccionaCbk2 == true) {
				By id = By.id(ByCte.BY_ID_CBK2);
				WebElement cbkLongTexto = driver.findElement(id);
				cbkLongTexto.click();
				timeouts.implicitlyWait(TimeCte._1800000000, TimeUnit.SECONDS);
				By id2 = By.id(ByCte.BY_ID_TAMANIO_TEXTO);
				WebElement longTexto = driver.findElement(id2);
				int valorLongTextoReq = config.getValorLongTextoReq();
				longTexto.sendKeys(valorLongTextoReq + BaseCte.BS_TXT_VACIO);
			}

			By id = By.id(ByCte.BY_ID_BTN_GUARDAR_ETIQUETA);
			WebElement findElement2 = driver.findElement(id);
			findElement2.click();

			if (!saleAlerta()) {
				miLogger.log(Level.INFO, "Se ha guardado etiqueta con nombre " + etiqueta);
				boolean verificarEtiquetaAgregada = verificarEtiquetaAgregada(etiqueta);
				return verificarEtiquetaAgregada;
			} else {
				miLogger.log(Level.INFO, "NO se ha guardado etiqueta con nombre " + etiqueta);
				TargetLocator switchTo = driver.switchTo();
				Alert alert = switchTo.alert();
				alert.accept();
				switchTo.defaultContent();
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean verificarEtiquetaAgregada(String etiqueta) {
		boolean agregado = false;
		try {
			Options manage = driver.manage();
			Timeouts timeouts = manage.timeouts();
			timeouts.implicitlyWait(TimeCte._1200000000, TimeUnit.SECONDS);
			By id = By.id(ByCte.BY_ID_LISTA_ETIQUETAS);
			WebElement listaEtiquetas = driver.findElement(id);
			timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
			By className = By.className(ByCte.BY_CLASS_NAME_COL_SM_6);
			List<WebElement> etiquetas = listaEtiquetas.findElements(className);
			Iterator<WebElement> i = etiquetas.iterator();
			int size = etiquetas.size();
			miLogger.log(Level.WARNING, "Tamanho lista etiquetas = " + size);

			/*
			 * for (WebElement ele: etiquetas) { miLogger.log(Level.INFO,
			 * "Texto de span o etiqueta = " + ele.getText()); miLogger.log(Level.INFO,
			 * etiqueta); agregado = (ele.getText().equalsIgnoreCase(etiqueta))?true:false;
			 * }
			 */

			while (i.hasNext() && agregado == false) {
				WebElement temp = i.next();
				String text = temp.getText();
				miLogger.log(Level.INFO, "Texto de span o etiqueta = " + text);
				miLogger.log(Level.INFO, "Mi etiqueta es " + etiqueta);
				boolean b = (text.equalsIgnoreCase(etiqueta)) ? true : false;
				agregado = b;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		miLogger.log(Level.INFO, "Valor de Agregado = " + agregado);
		// driver.navigate().refresh();
		return agregado;
	}

	public boolean verificarEtiquetaAgregadaConNuevaSesion(String etiqueta) {
		boolean agregado = false;
		try {
			TFInstanciaTramite instanciaTram = new TFInstanciaTramite();
			// int idReq = requisito.getIdRequisitoSelected();
			TFVerTramite tramite = requisito.getTramite();
			int idTram = tramite.getIdTramiteSelected();
			instanciaTram.corroborarEtiquetasTramite(idTram);

			By id = By.id(ByCte.BY_ID_LISTA_ETIQUETAS);
			WebElement listaEtiquetas = driver.findElement(id);
			Options manage = driver.manage();
			Timeouts timeouts = manage.timeouts();
			timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
			By className = By.className(ByCte.BY_CLASS_NAME_COL_SM_6);
			List<WebElement> etiquetas = listaEtiquetas.findElements(className);
			Iterator<WebElement> i = etiquetas.iterator();
			int size = etiquetas.size();
			miLogger.log(Level.WARNING, "Tamanho lista etiquetas = " + size);

			/*
			 * for (WebElement ele: etiquetas) { miLogger.log(Level.INFO,
			 * "Texto de span o etiqueta = " + ele.getText()); miLogger.log(Level.INFO,
			 * etiqueta); agregado = (ele.getText().equalsIgnoreCase(etiqueta))?true:false;
			 * }
			 */

			while (i.hasNext() && agregado == false) {
				WebElement temp = i.next();
				String text = temp.getText();
				miLogger.log(Level.INFO, "Texto de span o etiqueta = " + text);
				miLogger.log(Level.INFO, "Mi etiqueta es " + etiqueta);
				boolean equalsIgnoreCase = text.equalsIgnoreCase(etiqueta);
				boolean b = equalsIgnoreCase ? true : false;
				agregado = b;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		miLogger.log(Level.INFO, "Valor de Agregado = " + agregado);
		// driver.navigate().refresh();
		return agregado;
	}

	// ****************************************************************Este método
	// sirve para Guardar Etiquetas y
	// Formularios********************************************

	// se guarda cambio de etiquetas Y del formulario
	protected void guardarFormulario() {
		By id = By.id(ByCte.BY_ID_BTN_GUARDAR_FORMULARIO);
		WebElement findElement = driver.findElement(id);
		findElement.click();
	}

	// **************************************************************************Esto
	// es para modificar
	// Formulario*******************************************************

	public String modificarFormulario(String descrip, String cod) {
		Options manage = driver.manage();
		Timeouts timeouts = manage.timeouts();
		timeouts.implicitlyWait(TimeCte._1500000000, TimeUnit.SECONDS);
		By id = By.id(ByCte.BY_DESCRIPCION_FORMULARIO);
		WebElement descripcion = driver.findElement(id);
		By xpath = By.xpath(ByCte.BY_XPATH_CODIGO_FORMULARIO);
		WebElement codigo = driver.findElement(xpath);
		descripcion.clear();
		codigo.clear();
		descripcion.sendKeys(descrip);
		codigo.sendKeys(cod);

		guardarFormulario();
		if (!saleAlerta()) {
			String msg = "Se ha guardado formulario con descripcion " + descrip + " y codigo " + cod;
			miLogger.log(Level.INFO, msg);
		} else {
			miLogger.log(Level.INFO, "NO se ha guardado formulario con descripcion " + descrip + " y codigo " + cod);
			TargetLocator switchTo = driver.switchTo();
			Alert alert = switchTo.alert();
			alert.accept();
			switchTo.defaultContent();
		}
		return driver.getCurrentUrl();
	}

	public String modificarDescripFormulario(String descrip) {
		driver.manage().timeouts().implicitlyWait(TimeCte._1200000000, TimeUnit.SECONDS);
		By id = By.id(ByCte.BY_DESCRIPCION_FORMULARIO);
		WebElement descripcion = driver.findElement(id);
		descripcion.clear();
		descripcion.sendKeys(descrip);
		guardarFormulario();
		if (!saleAlerta()) {
			miLogger.log(Level.INFO, "Se ha guardado formulario con descripcion " + descrip);
		} else {
			miLogger.log(Level.INFO, "NO se ha guardado formulario con descripcion " + descrip);
			TargetLocator switchTo = driver.switchTo();
			Alert alert = switchTo.alert();
			alert.accept();
			switchTo.defaultContent();
		}
		return driver.getCurrentUrl();
	}

	public String modificarCodigoFormulario(String cod) {
		driver.manage().timeouts().implicitlyWait(TimeCte._1200000000, TimeUnit.SECONDS);
		By xpath = By.xpath(ByCte.BY_XPATH_CODIGO_FORMULARIO);
		WebElement codigo = driver.findElement(xpath);
		codigo.clear();
		codigo.sendKeys(cod);
		guardarFormulario();
		if (!saleAlerta()) {
			miLogger.log(Level.INFO, "Se ha guardado formulario con codigo " + cod);
		} else {
			miLogger.log(Level.INFO, "NO se ha guardado formulario con codigo " + cod);
			TargetLocator switchTo = driver.switchTo();
			Alert alert = switchTo.alert();
			alert.accept();
			switchTo.defaultContent();
		}
		return driver.getCurrentUrl();
	}

	public void modificarEtiquetas() {
		By id = By.id(ByCte.BY_ID_LISTA_ETIQUETAS);
		WebElement columnas = driver.findElement(id);
		Options manage = driver.manage();
		Timeouts timeouts = manage.timeouts();
		timeouts.implicitlyWait(TimeCte._1200000000, TimeUnit.SECONDS);
		By tagName = By.tagName(ByCte.BY_TAG_INPUT);
		List<WebElement> etiquetas = columnas.findElements(tagName);

		for (WebElement el : etiquetas) {
			el.sendKeys("sdfsmdmosd");
		}

	}

	public int getClicksAgregarComboVal() {
		return clicksAgregarComboVal;
	}

	public void setClicksAgregarComboVal(int clicksAgregarComboVal) {
		this.clicksAgregarComboVal = clicksAgregarComboVal;
	}

	protected boolean saleAlerta() {
		try {
			TargetLocator switchTo = driver.switchTo();
			Alert alert = switchTo.alert();
			String text = alert.getText();
			miLogger.log(Level.INFO, text);
			return true;
		} catch (NoAlertPresentException alEx) {
			miLogger.log(Level.INFO, "No aparecio ninguna alerta.");
			return false;
		}
	}

}
