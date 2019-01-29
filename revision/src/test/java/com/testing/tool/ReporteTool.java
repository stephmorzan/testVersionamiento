package com.testing.tool;

import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;

public class ReporteTool {

	private LineBuilderStringTool contenido;

	private Date tiempo;
	private String producto;
	private String version;
	private String casoDePrueba;
	private String autor;
	private List<String> urlOrigen;
	private List<String> urlDestino;
	

	public ReporteTool(Date tiempo) {
		super();
		this.tiempo = tiempo;
		contenido = new LineBuilderStringTool();
	}

	/**
	 * metodo que define la cabecera del reporte
	 * 
	 * @return
	 */
	public String getCabecera() {
		LineBuilderStringTool contenido = new LineBuilderStringTool();
		contenido.appendLine("Fecha    :" + tiempo.toString());
		contenido.appendLine("Hora     :" + tiempo.toString());
		contenido.appendSalto();
		contenido.appendLine("Producto :" + producto);
		contenido.appendLine("Prueba   :" + casoDePrueba);
		contenido.appendSalto();
		return contenido.toString();
	}

	/**
	 * metodo que define el pie del reporte
	 * 
	 * @return
	 */
	public String getPie() {
		LineBuilderStringTool contenido = new LineBuilderStringTool();
		contenido.appendSalto();
		contenido.appendLine("Autor    :" + autor);
		contenido.appendSalto();
		return contenido.toString();
	}

	public LineBuilderStringTool getContenido() {
		return contenido;
	}

	public void setContenido(LineBuilderStringTool asdf) {
		this.contenido = asdf;
	}

	public Date getTiempo() {
		return tiempo;
	}

	public void setTiempo(Date tiempo) {
		this.tiempo = tiempo;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCasoDePrueba() {
		return casoDePrueba;
	}

	public void setCasoDePrueba(String casoDePrueba) {
		this.casoDePrueba = casoDePrueba;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public List<String> getUrlOrigen() {
		return urlOrigen;
	}

	public void setUrlOrigen(List<String> urlOrigen) {
		this.urlOrigen = urlOrigen;
	}

	public List<String> getUrlDestino() {
		return urlDestino;
	}

	public void setUrlDestino(List<String> urlDestino) {
		this.urlDestino = urlDestino;
	}

	/**
	 * metodo delegado
	 * 
	 * @param str
	 */
	public void append(String str) {
		contenido.append(str);
	}

	/**
	 * metodo delegado
	 * 
	 * @param str
	 */
	public void appendLine(String str) {
		contenido.appendLine(str);
	}

	/**
	 * metodo delegado
	 */
	public void appendSalto() {
		contenido.appendSalto();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String resultado = "";
		resultado = getCabecera() + contenido.toString() + getPie();
		return resultado;
	}

	public void append(WebElement campUsuario) {
		contenido.append( campUsuario.getText() + " : " + campUsuario.getAttribute("value"));

	}
}
