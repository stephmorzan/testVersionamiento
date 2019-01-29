package com.testing.testflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.base.MetodosElementos;

public class TFVerTramite {

	MetodosElementos metodos = new MetodosElementos();
	WebDriver driver;
	Logger miLogger;
	TFVerTupa tupa = new TFVerTupa();
	int idTramiteSelected;

	public TFVerTramite() {
		// TODO Auto-generated constructor stub
		this.driver = tupa.getDriver();
		this.miLogger = tupa.getMiLogger();
		setUp();
		// listarIdTramites();
	}

	public void setUp() {
		tupa.seleccionarTupa();
	}

	public String buscarUltimoTramite() {
		WebElement cuerpoTabla = listarTramites(25/* Fabricator.alphaNumeric().randomInt(10, 40) */);
		Map<Integer, WebElement> verTramites = mapearVerTramites(cuerpoTabla);
		int mayorId = hallarUltimoTramiteEnBD(verTramites);
		System.out.println(mayorId);
		seleccionarVerTramite(verTramites, mayorId);
		return driver.getTitle();
	}

	public String seleccionarTramite(/* int id */) {
		WebElement cuerpoTabla = listarTramites(25);
		Map<Integer, WebElement> verTramites = mapearVerTramites(cuerpoTabla);
		List<Integer> tramites = new ArrayList<>();
		for (int id : verTramites.keySet()) {
			tramites.add(id);
		}
		Random random = new Random();
		int size = tramites.size();
		int randomIndex = random.nextInt(size);
		int indexAl = tramites.get(randomIndex);
		String x = "Valor del indice random de tramite = " + indexAl + "\nIndice de lista de tramites = " + randomIndex;
		System.out.println(x);
		this.setIdTramiteSelected(indexAl);
		seleccionarVerTramite(verTramites, indexAl/* id */);
		String title = driver.getTitle();
		return title;
	}

	protected WebElement listarTramites(int espera) {
		WebElement cargarTabla = metodos.cargarTabla(driver, espera);
		return cargarTabla;
	}

	private Map<Integer, WebElement> mapearVerTramites(WebElement cuerpoTabla) {
		Map<Integer, WebElement> mapearVerElementos = metodos.mapearVerElementos(cuerpoTabla, miLogger);
		return mapearVerElementos;
	}

	private int hallarUltimoTramiteEnBD(Map<Integer, WebElement> idButModif) {
		int hallarUltimoElementoEnBD = metodos.hallarUltimoElementoEnBD(idButModif);
		return hallarUltimoElementoEnBD;
	}

	private void seleccionarVerTramite(Map<Integer, WebElement> idButModif, int id) {
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

	public TFVerTupa getTupa() {
		return tupa;
	}

	public void setTupa(TFVerTupa tupa) {
		this.tupa = tupa;
	}

	public int getIdTramiteSelected() {
		return idTramiteSelected;
	}

	public void setIdTramiteSelected(int idTramiteSelected) {
		this.idTramiteSelected = idTramiteSelected;
	}

}
