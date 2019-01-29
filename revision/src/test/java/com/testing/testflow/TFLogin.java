package com.testing.testflow;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.testing.base.TestBase;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.ByCte;

public class TFLogin extends TestBase {

	String usuario;
	String clave;
	// ConfigFileReader config;

	public TFLogin() {
		super();
		// config = new ConfigFileReader();
		this.usuario = properties.getLoginUser();
		this.clave = properties.getLoginPassword();
	}

	public void loginOk() {
		By name = By.name(ByCte.BY_NAME_USERNAME);
		WebElement campUsuario = driver.findElement(name);
		By name2 = By.name(ByCte.BY_NAME_PASSWORD);
		WebElement campPassw = driver.findElement(name2);
		campUsuario.sendKeys(this.usuario);
		campPassw.sendKeys(this.clave);
		
		reporte.append(campUsuario);
		
		By id = By.id(ByCte.BY_ID_SUBMIT);
		WebElement findElement = driver.findElement(id);
		findElement.submit();
		Logger miLogger2 = super.getMiLogger();
		miLogger2.log(Level.FINE, "Se colocaron valores de login puestos en config.properties.");
	}

	public String login(String usuario, String clave) {
		By name = By.name(ByCte.BY_NAME_USERNAME);
		WebElement campUsuario = driver.findElement(name);
		By name2 = By.name(ByCte.BY_NAME_PASSWORD);
		WebElement campPassw = driver.findElement(name2);
		campUsuario.sendKeys(usuario);
		
		reporte.append(campUsuario);
		reporte.appendSalto();
		
		campPassw.sendKeys(clave);
		
		reporte.append(campPassw);
		reporte.appendSalto();
		
		By id = By.id(ByCte.BY_ID_SUBMIT);
		WebElement findElement = driver.findElement(id);
		findElement.submit();
		Logger miLogger2 = super.getMiLogger();
		miLogger2.log(Level.FINE, "Se colocaron valores de login puestos en config.properties.");
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public boolean sePodraLogin(String usuario, String clave) {
		String login = login(usuario, clave);
		Logger miLogger2 = super.getMiLogger();
		boolean sePudo = false;
		
		if (login.contains(BaseCte.BS_TXT_ERROR)) {
			miLogger2.log(Level.SEVERE, "Existe un error, por parte de usuario o de sistema.");
			reporte.appendLine("Se reporta error login");
			System.out.println(reporte.toString());
		} else {
			if (login.contains(BaseCte.BS_TXT_INICIO)) {
				miLogger2.log(Level.FINE, "Se ha logrado hacer login.");
				sePudo = true;
			}
		}
		return sePudo;
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	/*
	 * public ConfigFileReader getConfig() { return config; }
	 * 
	 * public void setConfig(ConfigFileReader config) { this.config = config; }
	 */
}
