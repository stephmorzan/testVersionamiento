package com.testing.support;

import java.util.List;

import org.openqa.selenium.WebElement;

public class ComponenteHtml {

	protected int idComponente;
	private String nombreEtiqueta;
	private String tipoValorEtiqueta;
	protected WebElement butEliminar;
	protected List<String> valoresCombo;
	
	public ComponenteHtml() {
		// TODO Auto-generated constructor stub
	}

	public int getIdComponente() {
		return idComponente;
	}

	public void setIdComponente(int idComponente) {
		this.idComponente = idComponente;
	}

	public String getNombreEtiqueta() {
		return nombreEtiqueta;
	}

	public void setNombreEtiqueta(String nombreEtiqueta) {
		this.nombreEtiqueta = nombreEtiqueta;
	}

	public WebElement getButEliminar() {
		return butEliminar;
	}

	public void setButEliminar(WebElement butEliminar) {
		this.butEliminar = butEliminar;
	}

	public String getTipoValorEtiqueta() {
		return tipoValorEtiqueta;
	}

	public void setTipoValorEtiqueta(String tipoValorEtiqueta) {
		this.tipoValorEtiqueta = tipoValorEtiqueta;
	}

	public List<String> getValoresCombo() {
		return valoresCombo;
	}

	public void setValoresCombo(List<String> valoresCombo) {
		this.valoresCombo = valoresCombo;
	}
	
}
