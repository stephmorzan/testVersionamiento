package com.testing.testflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testing.support.ComponenteHtml;

public class TFRecorrerComponente {

	WebDriver driver;
	Logger miLogger;
	TFVerRequisito requisito;
	int clicksAgregarComboVal;
	String randomCadenaComboVal;

	public TFRecorrerComponente() {
		// TODO Auto-generated constructor stub
		requisito = new TFVerRequisito();
		this.driver = requisito.getDriver();
		this.miLogger = requisito.getMiLogger();
		setUp();
	}

	public void setUp(){
		//System.out.println(ReflectionTool.gettearNombreMetodoActual());
		requisito.buscarEditarUltimoRequisito();
		//requisito.seleccionarEditarRequisito();
	}
	
	@Test
	public void verificarComponentesCreados() {
		guardarComponentes();
		moverseInstanciaTramite();
	}

	public void moverseInstanciaTramite() {

		try {
			TFInstanciaTramite instanciaTram = new TFInstanciaTramite();
			int idReq = requisito.getIdRequisitoSelected();
			int idTram = requisito.getTramite().getIdTramiteSelected();
			instanciaTram.corroborarEtiquetasTramite(idTram);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			//new TestInstanciaRequisito().listarRequisitos(25);
			//new MetodosElementos().listarRequisitos(30, driver);


		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public List<ComponenteHtml> guardarComponentes() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement listaEtiquetas = driver.findElement(By.id("listaEtiquetas"));
		//System.out.println(listaEtiquetas.getAttribute("id"));
		//Sí existe este objeto, tenga o no objetos. Falta ver si tiene objetos adentro.
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.MILLISECONDS);
		List<WebElement> componentes = listaEtiquetas.findElements(By.cssSelector("div[id*=\"divComponente\"]"));
		Iterator<WebElement> i = componentes.iterator();
		List<ComponenteHtml> campos = new ArrayList<>();

		boolean componentesIsEmpty = componentes.isEmpty();

		if(!componentesIsEmpty) {
			System.out.println("Componentes no esta vacio");
			Pattern p = Pattern.compile(".+[^\\d]");
			Matcher m;
			while(i.hasNext()) {
				WebElement temp = i.next();
				ComponenteHtml componenteHtml = new ComponenteHtml();
				List<String> valoresCombo = new ArrayList<>();
				m = p.matcher(temp.getAttribute("id"));
				int idComponente = Integer.parseInt(m.replaceAll(""));
				componenteHtml.setIdComponente(idComponente);
				componenteHtml.setNombreEtiqueta(temp.findElement(By.tagName("span")).getText());
				componenteHtml.setButEliminar(temp.findElement(By.cssSelector("span[onclick*=\"eliminarComponente\"]")));
				System.out.println(temp.getAttribute("id") + " - " + temp.getTagName() + " - " + temp.getAttribute("class"));
				WebElement campo = temp.findElement(By.id(idComponente+""));
				String tipoCampoValorComp = campo.getTagName();
				componenteHtml.setTipoValorEtiqueta(tipoCampoValorComp);

				if(tipoCampoValorComp.equalsIgnoreCase("select")) {
					List<WebElement> options = temp.findElements(By.tagName("option"));
					for (WebElement webe : options) {
						String value = webe.getText();
						System.out.println(value);
						valoresCombo.add(value);
					}
					componenteHtml.setValoresCombo(valoresCombo);
				}

				campos.add(componenteHtml);
			}
		}

		return campos;

	}
}
