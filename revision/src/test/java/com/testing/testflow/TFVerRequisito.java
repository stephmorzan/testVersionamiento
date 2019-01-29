package com.testing.testflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testing.base.MetodosElementos;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.ByCte;
import com.testing.tool.cte.RegExpCte;

public class TFVerRequisito {

	private static final String ID_REQUISITO = "Id requisito = ";
	
	MetodosElementos metodos = new MetodosElementos();
	WebDriver driver;
	Logger miLogger;
	TFVerTramite tramite = new TFVerTramite();
	int idRequisitoSelected;

	public TFVerRequisito() {
		// TODO Auto-generated constructor stub
		this.driver = tramite.getDriver();
		this.miLogger = tramite.getMiLogger();
		setUp();
	}

	public void setUp() {
		// tramite.buscarUltimoTramite();
		tramite.seleccionarTramite(/* 242 */);
	}

	public String buscarEditarUltimoRequisito() {
		WebElement cuerpoTabla = listarRequisitos(30);
		Map<Integer, WebElement> verRequisitos = mapearVerRequisitos(cuerpoTabla);
		int mayorId = hallarUltimoRequisitoEnBD(verRequisitos);
		seleccionarVerRequisito(verRequisitos, mayorId);
		return driver.getCurrentUrl();
	}

	public String seleccionarEditarRequisito() {
		try {
			WebElement cuerpoTabla = listarRequisitos(40);
			Map<Integer, WebElement> verRequisitos = mapearVerRequisitos(cuerpoTabla);

			List<Integer> requisitos = new ArrayList<>();
			for (int id : verRequisitos.keySet()) {
				requisitos.add(id);
			}
			Random random = new Random();
			int size = requisitos.size();
			int randomIndex = random.nextInt(size);
			int indexAl = requisitos.get(randomIndex);
			String x = "Valor del indice random de requisito = " + indexAl + "\nIndice de lista de requisitos = " + randomIndex;
			System.out.println(x);

			this.setIdRequisitoSelected(indexAl);
			seleccionarVerRequisito(verRequisitos, indexAl);

		} catch (Exception e) {
			String message = e.getMessage();
			System.out.println(message);
			e.printStackTrace();
		}
		return driver.getCurrentUrl();
	}

	private WebElement listarRequisitos(int espera) {
		By id = By.id(ByCte.BY_ID_CUERPO_TABLA);
		WebElement cuerpoTabla = driver.findElement(id);
		WebDriverWait wait = new WebDriverWait(driver, espera);
		By xpath = By.xpath(ByCte.BY_XPATH_TR_TD);
		ExpectedCondition<WebElement> presenceOfNestedElementLocatedBy = ExpectedConditions.presenceOfNestedElementLocatedBy(cuerpoTabla, xpath);
		wait.until(presenceOfNestedElementLocatedBy);
		return cuerpoTabla;
	}

	private Map<Integer, WebElement> mapearVerRequisitos(WebElement cuerpoTabla) {
		By id = By.id(ByCte.BY_ID_BTN_EDITAR_FORMULARIO);
		List<WebElement> butsEditar = cuerpoTabla.findElements(id);
		Iterator<WebElement> i = butsEditar.iterator();

		int intId = 0;
		Map<Integer, WebElement> idButEditar = new HashMap<>();
		Pattern p = Pattern.compile(RegExpCte.RGX_D);

		while (i.hasNext()) {
			WebElement temp = i.next();
			String attribute = temp.getAttribute(ByCte.BY_ATTR_ONCLICK);
			Matcher m = p.matcher(attribute);
			// miLogger.log(Level.INFO, driver.findElement(By.xpath("//*[contains(text(),
			// 'OBLIGATORIO')]")).getText());
			String replaceAll = m.replaceAll(BaseCte.BS_TXT_VACIO);
			intId = Integer.parseInt(replaceAll);
			String msg = ID_REQUISITO + intId;
			miLogger.log(Level.INFO, msg);
			idButEditar.put(intId, temp);
		}

		return idButEditar;
	}

	private int hallarUltimoRequisitoEnBD(Map<Integer, WebElement> idBut) {
		return metodos.hallarUltimoElementoEnBD(idBut);
	}

	private void seleccionarVerRequisito(Map<Integer, WebElement> idButModif, int id) {
		WebElement ultimoAgregado = idButModif.get(id);
		By id2 = By.id(ByCte.BY_ID_BTN_EDITAR_FORMULARIO);
		WebElement findElement = ultimoAgregado.findElement(id2);
		findElement.click();
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
		return tramite;
	}

	public void setTramite(TFVerTramite tramite) {
		this.tramite = tramite;
	}

	public int getIdRequisitoSelected() {
		return idRequisitoSelected;
	}

	public void setIdRequisitoSelected(int idRequisitoSelected) {
		this.idRequisitoSelected = idRequisitoSelected;
	}

}
