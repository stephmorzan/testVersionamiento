package com.testing.testflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;

import com.testing.base.MetodosElementos;
import com.testing.support.Requisito;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.ByCte;
import com.testing.tool.cte.RegExpCte;
import com.testing.tool.cte.TimeCte;

public class TFClonarRequisito {

	
	private static final String STRING = "'";
	private static final String A_HREF_REQUISITO_ID = "a[href*='requisito?id=";
	
	MetodosElementos metodos = new MetodosElementos();
	WebDriver driver;
	Logger miLogger;
	TFVerTramite testTramite = new TFVerTramite();

	public TFClonarRequisito() {
		this.driver = testTramite.getDriver();
		this.miLogger = testTramite.getMiLogger();
		setUp();
	}

	public void setUp() {
		testTramite.seleccionarTramite(/* 241 */);
		// testTramite.buscarUltimoTramite();
	}

	public boolean clonarRequisitos() {

		Navigation navigate = driver.navigate();
		navigate.back();
		List<Integer> antes = listarIdTramites();
		navigate.forward();
		List<Requisito> requisitos = guardarFilasRequisitos();
		String currentUrl = driver.getCurrentUrl();
		miLogger.log(Level.INFO, "Url del requisito a clonar = " + currentUrl);
		Random random = new Random();
		int size = requisitos.size();
		int indexAl = random.nextInt(size);
		System.out.println("Aleatorio: Indice del requisito a clonar = " + indexAl);
		Requisito original = requisitos.get(indexAl);

		original.getButClonar().click();

		Options manage = driver.manage();
		Timeouts timeouts = manage.timeouts();
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		navigate.refresh();
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		// Va para tramites. Aun no se actualiza
		navigate.back();
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		// Va para Tupas. Sigue sin actualizarse
		navigate.back();
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		// Vuelve a Tramites. Ahora SI se actualiza
		navigate.forward();
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		// Por si acaso, se actualiza
		navigate.refresh();

		// List<Tramite> despues = listarIdTramites();
		List<Integer> despues = listarIdTramites();

		System.out.println("Tamanho lista antes = " + antes.size());
		System.out.println("Tamanho lista despues = " + despues.size());

		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		timeouts.implicitlyWait(TimeCte._2100000000, TimeUnit.SECONDS);
		navigate.refresh();

		// Sacar los nuevos tramites generados o clonados
		List<Integer> nuevos = hallarNuevosTramites(antes, despues);

		System.out.println("Tamanho lista reqsNuevos = " + nuevos.size());
		Iterator<Integer> iteNuevos = nuevos.iterator();
		// ConsultasBD consulta = new ConsultasBD();
		boolean encontrado = false;

		while (iteNuevos.hasNext() && encontrado == false) {
			System.out.println("Entro al while para buscar en la BD con el idTramite de los nuevos");
			int tempId = iteNuevos.next();
			String selector = A_HREF_REQUISITO_ID + tempId + STRING;
			By cssSelector = By.cssSelector(selector);
			WebElement findElement = driver.findElement(cssSelector);
			findElement.click();
			List<Requisito> reqsNuevo = guardarFilasRequisitos();
			int diferencias = 0;

			if (reqsNuevo.size() == size) {
				for (int k = 0; k < size; k++) {
					Requisito re1 = reqsNuevo.get(k);
					Requisito re2 = requisitos.get(k);
					int trues = 0;

					if (re1.getIdReq() != re2.getIdReq()) {
						System.out.println(re1.getIdReq());
						System.out.println(re2.getIdReq());
						System.out.println("Valor de trues antes= " + trues);
						trues = trues + 1;
						System.out.println("Valor de trues ahora1= " + trues);
					}

					if (re1.getVersionReq() != re2.getVersionReq()) {
						System.out.println(re1.getVersionReq());
						System.out.println(re2.getVersionReq());
						System.out.println("Valor de trues antes= " + trues);
						trues = trues + 1;
						System.out.println("Valor de trues ahora2= " + trues);
					}

					if (re1.getIdForm() != re2.getIdForm()) {
						System.out.println(re1.getIdForm());
						System.out.println(re2.getIdForm());
						System.out.println("Valor de trues antes= " + trues);
						trues = trues + 1;
						System.out.println("Valor de trues ahora3= " + trues);
					}

					if (re1.getVerForm() != re2.getVerForm()) {
						System.out.println(re1.getVerForm());
						System.out.println(re2.getVerForm());
						System.out.println("Valor de trues antes= " + trues);
						trues = trues + 1;
						System.out.println("Valor de trues ahora4= " + trues);
					}

					if (re1.getNombre().equals(re2.getNombre())) {
						System.out.println(re1.getNombre());
						System.out.println(re2.getNombre());
						System.out.println("Valor de trues antes= " + trues);
						trues = trues + 1;
						System.out.println("Valor de trues ahora5= " + trues);
					}

					if (re1.getCodForm().equals(re2.getCodForm())) {
						System.out.println(re1.getCodForm());
						System.out.println(re2.getCodForm());
						System.out.println("Valor de trues antes= " + trues);
						trues = trues + 1;
						System.out.println("Valor de trues ahora6= " + trues);
					}

					System.out.println("---------------------------------------------------------------------------------------------------------------");

					if (trues == 6) {
						diferencias++;
						System.out.println("Diferencias == " + diferencias);
						System.out.println("******************************************************************************");
					}

				}

				System.out.println("Fuera del For. Valor de Diferencias = " + diferencias);
				if (diferencias == 1) {
					miLogger.log(Level.INFO, "Se encontró en clon en la Base de Datos.");
					encontrado = true;
					miLogger.log(Level.INFO, "Url del requisito clonado = " + currentUrl);
				}
			}
			/*
			 * List<Requisito> consultReqs =
			 * consulta.buscarClonTramiteRequisito(original.getIdTramite(), tempId); int
			 * diferencias = 0;
			 * 
			 * for (int k=0; k<consultReqs.size(); k+=2) { Requisito re1 =
			 * consultReqs.get(k); Requisito re2 = consultReqs.get(k+1); int trues = 0;
			 * 
			 * if(re1.getIdReq()!=re2.getIdReq()) { System.out.println(re1.getIdReq());
			 * System.out.println(re2.getIdReq());
			 * System.out.println("Valor de trues antes= " + trues); trues=trues+1;
			 * System.out.println("Valor de trues ahora1= " + trues); }
			 * 
			 * if(re1.getVersionReq()!=re2.getVersionReq()) {
			 * System.out.println(re1.getVersionReq());
			 * System.out.println(re2.getVersionReq());
			 * System.out.println("Valor de trues antes= " + trues); trues=trues+1;
			 * System.out.println("Valor de trues ahora2= " + trues); }
			 * if(re1.getIdForm()!=re2.getIdForm()) { System.out.println(re1.getIdForm());
			 * System.out.println(re2.getIdForm());
			 * System.out.println("Valor de trues antes= " + trues); trues=trues+1;
			 * System.out.println("Valor de trues ahora3= " + trues); }
			 * 
			 * if(re1.getVerForm()!=re2.getVerForm()) {
			 * System.out.println(re1.getVerForm()); System.out.println(re2.getVerForm());
			 * System.out.println("Valor de trues antes= " + trues); trues=trues+1;
			 * System.out.println("Valor de trues ahora4= " + trues); }
			 * 
			 * if(re1.getDenomReq().equals(re2.getDenomReq())) {
			 * System.out.println(re1.getDenomReq()); System.out.println(re2.getDenomReq());
			 * System.out.println("Valor de trues antes= " + trues); trues=trues+1;
			 * System.out.println("Valor de trues ahora5= " + trues); }
			 * 
			 * if(re1.getCodForm().equals(re2.getCodForm())) {
			 * System.out.println(re1.getCodForm()); System.out.println(re2.getCodForm());
			 * System.out.println("Valor de trues antes= " + trues); trues=trues+1;
			 * System.out.println("Valor de trues ahora6= " + trues); }
			 * 
			 * System.out.println(
			 * "---------------------------------------------------------------------------------------------------------------"
			 * );
			 * 
			 * 
			 * if(trues==6) { diferencias++; System.out.println("Diferencias == " +
			 * diferencias); System.out.println(
			 * "******************************************************************************"
			 * ); }
			 * 
			 * 
			 * }
			 * 
			 * System.out.println("Fuera del For. Valor de Diferencias = " + diferencias);
			 * if(diferencias == 1) { miLogger.log(Level.INFO,
			 * "Se encontró en clon en la Base de Datos."); encontrado=true; }
			 */
		}

		return encontrado;

	}

	private List<Integer> listarIdTramites() {
		WebElement cuerpoTabla = testTramite.listarTramites(20);
		List<Integer> tramites = new ArrayList<>();
		By tagName = By.tagName(ByCte.BY_TAG_TR);
		List<WebElement> filasElementos = cuerpoTabla.findElements(tagName);
		Iterator<WebElement> i = filasElementos.iterator();

		while (i.hasNext()) {
			WebElement temp = i.next();
			By tagName2 = By.tagName(ByCte.BY_TAG_TD);
			List<WebElement> columnas = temp.findElements(tagName2);

			By tagName3 = By.tagName(ByCte.BY_TAG_A);
			WebElement webElement = columnas.get(5);
			WebElement butEditar = webElement.findElement(tagName3);
			Pattern p = Pattern.compile(RegExpCte.RGX_SUMA);
			String attribute = butEditar.getAttribute(ByCte.BY_ATTRIB_HREF);
			Matcher m = p.matcher(attribute);
			String replaceAll = m.replaceAll(BaseCte.BS_TXT_VACIO);
			int parseInt = Integer.parseInt(replaceAll);
			tramites.add(parseInt);
		}

		// System.out.println("******************************Se acabaron los
		// tramites*********************************************");
		return tramites;

	}

	private List<Requisito> guardarFilasRequisitos() {
		MetodosElementos metodosElementos = new MetodosElementos();
		WebElement cuerpoTabla = metodosElementos.listarRequisitos(driver, 35);
		By tagName = By.tagName(ByCte.BY_TAG_TR);
		List<WebElement> filasReq = cuerpoTabla.findElements(tagName);
		Iterator<WebElement> i = filasReq.iterator();
		Pattern p = Pattern.compile(RegExpCte.RGX_S);
		Matcher m;
		List<Requisito> requisitos = new ArrayList<>();

		while (i.hasNext()) {
			WebElement temp = i.next();
			By tagName2 = By.tagName(ByCte.BY_TAG_TD);
			List<WebElement> columnas = temp.findElements(tagName2);
			/*
			 * for (WebElement e: columnas) { miLogger.log(Level.SEVERE, e.getText()); }
			 */
			Requisito requisito = new Requisito();
			WebElement webElement = columnas.get(1);
			requisito.setNombre(webElement.getText());
			WebElement webElement2 = columnas.get(2);
			requisito.setTipo(webElement2.getText());
			WebElement webElement3 = columnas.get(3);
			int parseInt = Integer.parseInt(webElement3.getText());
			requisito.setVersionReq(parseInt);
			WebElement webElement4 = columnas.get(4);
			By id = By.id(ByCte.BY_ID_BTN_EDITAR_FORMULARIO);
			WebElement findElement = webElement4.findElement(id);
			requisito.setButEditar(findElement);
			By id2 = By.id(ByCte.BY_ID_BTN_ACTUALIZAR_REQUISITO);
			WebElement findElement2 = webElement4.findElement(id2);
			requisito.setButClonar(findElement2);

			By tagName3 = By.tagName(ByCte.BY_TAG_LI);
			List<WebElement> elementosUl = webElement4.findElements(tagName3);

			WebElement webElement5 = elementosUl.get(0);
			m = p.matcher(webElement5.getText());
			String replaceAll = m.replaceAll(BaseCte.BS_TXT_VACIO);
			requisito.setCodForm(replaceAll);
			WebElement webElement6 = elementosUl.get(1);
			m = p.matcher(webElement6.getText());
			int parseInt2 = Integer.parseInt(replaceAll);
			requisito.setVerForm(parseInt2);
			// System.out.println(requisito.getVerForm());
			requisito.setIdRequisitoYFormulario();
			requisito.setIdTramite(driver.getCurrentUrl());
			// System.out.println(requisito.getIdTramite());
			requisitos.add(requisito);
			// System.out.println("Se ha agregado requisito a la lista.");
			// System.out.println("**************************************************************************************************");
		}

		return requisitos;
	}

	private List<Integer> hallarNuevosTramites(List<Integer> antes, List<Integer> despues) {
		List<Integer> nuevos = new ArrayList<>();

		for (int tramite : despues) {
			if (antes.contains(tramite)) {
				// System.out.println("Antes Si contiene este tramite: " + tramite);
			} else {
				// System.out.println("Antes No contiene este tramite: " + tramite);
				nuevos.add(tramite);
			}
		}
		return nuevos;
	}

	public MetodosElementos getMetodos() {
		return metodos;
	}

	public void setMetodos(MetodosElementos metodos) {
		this.metodos = metodos;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public Logger getMiLogger() {
		return miLogger;
	}

	public void setMiLogger(Logger miLogger) {
		this.miLogger = miLogger;
	}

	public TFVerTramite getTramite() {
		return testTramite;
	}

	public void setTramite(TFVerTramite tramite) {
		this.testTramite = tramite;
	}

}
