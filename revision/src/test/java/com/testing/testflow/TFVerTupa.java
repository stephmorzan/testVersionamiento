package com.testing.testflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.base.MetodosElementos;

public class TFVerTupa {

	MetodosElementos metodos = new MetodosElementos();
	WebDriver driver;
	Logger miLogger;
	TFLogin login = new TFLogin();
	int idTupaSelected;
	
	public TFVerTupa() {
		// TODO Auto-generated constructor stub
		this.driver = login.getDriver();
		this.miLogger = login.getMiLogger();
		setUp();
	}
	
	public void setUp() {
		login.loginOk();
	}
	
	public String buscarUltimoTupa() {
		//setUp();
		WebElement cuerpoTabla = listarTupas(20);
		Map<Integer,WebElement> verTupas = mapearVerTupas(cuerpoTabla);
		int mayorId= hallarUltimoTupaEnBD(verTupas);
		seleccionarVerTupa(verTupas, mayorId);
		String title = driver.getTitle();
		return title;
	}
	
	public String seleccionarTupa(/*int id*/) {
		WebElement cuerpoTabla = listarTupas(20);
		Map<Integer,WebElement> verTupas = mapearVerTupas(cuerpoTabla);
		
		List<Integer> tupas = new ArrayList<>();
		for(int id : verTupas.keySet()) {
			tupas.add(id);
		}
		Random random = new Random();
		int size = tupas.size();
		int randomIndex = random.nextInt(size);
		int indexAl = tupas.get(randomIndex);
		System.out.println("Valor del indice random de tupa = " + indexAl
		+ "\nIndice de lista de tupas = " + randomIndex);		
		
		seleccionarVerTupa(verTupas, indexAl);
		this.setIdTupaSelected(indexAl);
		return driver.getTitle();
	}
	
	protected WebElement listarTupas(int espera) {
		return metodos.cargarTabla(driver, espera);
	}
	
	protected Map<Integer,WebElement> mapearVerTupas(WebElement cuerpoTabla) {
		return metodos.mapearVerElementos(cuerpoTabla, miLogger);
	}
	
	protected int hallarUltimoTupaEnBD(Map<Integer,WebElement> idButModif) {
		return metodos.hallarUltimoElementoEnBD(idButModif);
	}
	
	protected void seleccionarVerTupa(Map<Integer,WebElement> idButModif, int id) {
		metodos.seleccionarVerElemento(idButModif, id);
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

	public TFLogin getLogin() {
		return login;
	}

	public void setLogin(TFLogin login) {
		this.login = login;
	}

	public int getIdTupaSelected() {
		return idTupaSelected;
	}

	public void setIdTupaSelected(int idTupa) {
		this.idTupaSelected = idTupa;
	}
	
}
