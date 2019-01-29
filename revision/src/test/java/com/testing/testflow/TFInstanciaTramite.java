package com.testing.testflow;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.testing.tool.ReflectionTool;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.ByCte;

public class TFInstanciaTramite {

	private static final String INSTANCIA_TRAMITE_JSP = "instanciaTramite.jsp";
	
	WebDriver driver;
	Logger miLogger;
	TFLogin login = new TFLogin();

	public TFInstanciaTramite() {
		// TODO Auto-generated constructor stub
		this.driver = login.getDriver();
		this.miLogger = login.getMiLogger();
		setUp();
		Navigation navigate = this.driver.navigate();
		navigate.to(BaseCte.BS_HTTP_LOCALHOST_8081_MINCETUR + INSTANCIA_TRAMITE_JSP);
	}

	public void setUp() {
		//System.out.println(ReflectionTool.gettearNombreMetodoActual());
		login.loginOk();
	}

	public void empezarTramite() {
		// WebElement nombreCliente = driver.findElement(By.id("nombreCliente"));
		// WebElement fechaTramite = driver.findElement(By.id("fechaTramite"));
		// Select se construye con Ajax. Crear metodo de listarTabla exclusivo para
		// ComboBox de instanciaTramite
		By id = By.id(ByCte.BY_ID_BTN_EMPEZAR_TRAMITE);
		driver.findElement(id).click();
	}

	public void corroborarEtiquetasTramite(int idTramite) {
		// WebElement nombreCliente = driver.findElement(By.id("nombreCliente"));
		// WebElement fechaTramite = driver.findElement(By.id("fechaTramite"));
		// Select se construye con Ajax. Crear metodo de listarTabla exclusivo para
		// ComboBox de instanciaTramite
		By id = By.id(ByCte.BY_ID_TRAMITE);
		WebElement findElement = driver.findElement(id);
		Select tramites = new Select(findElement);
		/* tramites.selectByVisibleText(tramite); */
		tramites.selectByValue(idTramite + "");
		By id2 = By.id(ByCte.BY_ID_BTN_EMPEZAR_TRAMITE);
		WebElement findElement2 = driver.findElement(id2);
		findElement2.click();
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

	public TFLogin getLogin() {
		return login;
	}

	public void setLogin(TFLogin login) {
		this.login = login;
	}

}
