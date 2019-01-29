package com.testing.base;

import java.util.Date;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import com.testing.dataProvider.ConfigFileReader;
import com.testing.tool.ReporteTool;
import com.testing.tool.cte.BaseCte;

public class TestBase {

	protected WebDriver driver;
	protected Logger miLogger = Logger.getLogger(BaseCte.BS_MI_LOGGER);
	protected ConfigFileReader properties;

	protected final String perfilQA;
	protected final String baseUrl;

	protected ReporteTool reporte;

	public TestBase() {
		// TODO Auto-generated constructor stub
		properties = new ConfigFileReader();
		baseUrl = properties.getBaseUrl();
		perfilQA = properties.getPerfilDriver();
		ProfilesIni perfil = new ProfilesIni();
		FirefoxProfile miPerfil = perfil.getProfile(perfilQA);
		this.driver = new FirefoxDriver(miPerfil);
		this.driver.get(baseUrl);
		reporte = new ReporteTool(new Date());
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

	public String getPerfilQA() {
		return perfilQA;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public ReporteTool getReporte() {
		return reporte;
	}

	public void setReporte(ReporteTool reporte) {
		this.reporte = reporte;
	}

}
