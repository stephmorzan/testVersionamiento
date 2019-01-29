package com.testing.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.ByCte;
import com.testing.tool.cte.RegExpCte;

public class MetodosElementos {

	private static final String ATTR_HREF = "href";
	private static final String ID_IGUAL_A = "Id = ";
	
	public WebElement cargarTabla(WebDriver driver, int espera) {
		By id = By.id(ByCte.BY_ID_CUERPO_TABLA);
		WebElement cuerpoTabla = driver.findElement(id);
		WebDriverWait wait = new WebDriverWait(driver, espera);
		By xpath = By.xpath(ByCte.BY_XPATH_TR_TD_A);
		ExpectedCondition<WebElement> presenceOfNestedElementLocatedBy = ExpectedConditions.presenceOfNestedElementLocatedBy(cuerpoTabla, xpath);
		wait.until(presenceOfNestedElementLocatedBy);
		return cuerpoTabla;
	}

	public WebElement listarRequisitos(WebDriver driver, int espera) {
		By id = By.id(ByCte.BY_ID_CUERPO_TABLA);
		WebElement cuerpoTabla = driver.findElement(id);
		WebDriverWait wait = new WebDriverWait(driver, espera);
		By xpath = By.xpath(ByCte.BY_XPATH_TR_TD);
		ExpectedCondition<WebElement> presenceOfNestedElementLocatedBy = ExpectedConditions.presenceOfNestedElementLocatedBy(cuerpoTabla, xpath);
		wait.until(presenceOfNestedElementLocatedBy);
		return cuerpoTabla;
	}

	public Map<Integer, WebElement> mapearVerElementos(WebElement cuerpoTabla, Logger miLogger) {
		By tagName = By.tagName(ByCte.BY_TAG_A);
		List<WebElement> butsVerElementos = cuerpoTabla.findElements(tagName);
		Iterator<WebElement> i = butsVerElementos.iterator();
		int id = 0;

		Map<Integer, WebElement> idButModif = new HashMap<>();
		Pattern p = Pattern.compile(RegExpCte.RGX_MULTI);

		// Hallar el último elemento ingresado (como id es secuencial, el ultimo es el
		// id mayor)
		while (i.hasNext()) {

			WebElement temp = i.next();
			String attribute = temp.getAttribute(ATTR_HREF);
			Matcher m = p.matcher(attribute);
			String replaceAll = m.replaceAll(BaseCte.BS_TXT_VACIO);
			id = Integer.parseInt(replaceAll);
			String msg = ID_IGUAL_A + id;
			miLogger.log(Level.INFO, msg);
			idButModif.put(id, temp);
		}

		return idButModif;
	}

	public int hallarUltimoElementoEnBD(Map<Integer, WebElement> idButton) {
		int mayor = 0;
		for (int i : idButton.keySet()) {
			mayor = (mayor < i) ? i : mayor;
		}
		return mayor;
	}

	public void seleccionarVerElemento(Map<Integer, WebElement> idButModif, int id) {
		WebElement ultimoAgregado = idButModif.get(id);
		By tagName = By.tagName(ByCte.BY_TAGNAME_BUTTON);
		ultimoAgregado.findElement(tagName).click();
	}

}
