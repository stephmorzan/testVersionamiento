package com.testing.testflow;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.base.MetodosElementos;

public class TFInstanciaRequisito {
	
	MetodosElementos metodos = new MetodosElementos();
	WebDriver driver;
	Logger miLogger;
	TFInstanciaTramite instanciaTramite = new TFInstanciaTramite();

	public TFInstanciaRequisito() {
		// TODO Auto-generated constructor stub
		this.driver = instanciaTramite.getDriver();
		this.miLogger = instanciaTramite.getMiLogger();
		setUp();
	}
	
	public void setUp() {
		instanciaTramite.empezarTramite();
	}
	
	public void completarRequisito() {
		//WebElement cuerpoTabla = listarRequisitos(25);
	}

	protected WebElement listarRequisitos(int espera) {
		return metodos.cargarTabla(driver, espera);
	}
	
}
