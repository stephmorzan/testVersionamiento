package com.testing.support;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.RegExpCte;

public class Requisito {

	protected String nombre;
	protected String tipo;
	protected int versionReq;
	protected WebElement ul;
	protected WebElement butEditar;
	protected WebElement butClonar;
	protected String codForm;
	protected int verForm;
	protected int idReq;
	protected int idForm;
	protected int idTramite;
	protected String denomReq;
	protected List<Requisito> otrosReqs;
	
	
	public Requisito() {
		// TODO Auto-generated constructor stub
	}

	public void setIdRequisitoYFormulario() {
		Pattern p = Pattern.compile(RegExpCte.RGX_D_D);
		String attribute = butClonar.getAttribute(BaseCte.ATTR_ONCLICK);
		Matcher m = p.matcher(attribute);
		while(m.find()) {
			String group = m.group(1);
			int parseInt = Integer.parseInt(group);
			this.setIdReq(parseInt);
			int idReq2 = this.getIdReq();
			System.out.println(idReq2);
			String group2 = m.group(2);
			int parseInt2 = Integer.parseInt(group2);
			this.setIdForm(parseInt2);
			int idForm2 = this.getIdForm();
			System.out.println(idForm2);
		}
		
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getVersionReq() {
		return versionReq;
	}

	public void setVersionReq(int versionReq) {
		this.versionReq = versionReq;
	}

	public WebElement getButEditar() {
		return butEditar;
	}

	public void setButEditar(WebElement butEditar) {
		this.butEditar = butEditar;
	}

	public WebElement getButClonar() {
		return butClonar;
	}

	public void setButClonar(WebElement butClonar) {
		this.butClonar = butClonar;
	}

	public WebElement getUl() {
		return ul;
	}

	public void setUl(WebElement ul) {
		this.ul = ul;
	}

	public String getCodForm() {
		return codForm;
	}

	public void setCodForm(String codForm) {
		this.codForm = codForm;
	}

	public int getVerForm() {
		return verForm;
	}

	public void setVerForm(int verForm) {
		this.verForm = verForm;
	}
	
	public int getIdForm() {
		return idForm;
	}
	
	public void setIdForm(int idForm) {
		this.idForm = idForm;
	}
	
	public int getIdReq() {
		return idReq;
	}
	
	public void setIdReq(int idReq) {
		this.idReq = idReq;
	}

	public int getIdTramite() {
		return idTramite;
	}

	public void setIdTramite(String url) {
		Pattern p = Pattern.compile(RegExpCte.RGX_SUMA);
		Matcher m = p.matcher(url);
		String replaceAll = m.replaceAll(BaseCte.BS_TXT_VACIO);
		int idTramite = Integer.parseInt(replaceAll);
		this.idTramite = idTramite;
	}

	public List<Requisito> getOtrosReqs() {
		return otrosReqs;
	}

	public void setOtrosReqs(List<Requisito> otrosReqs) {
		this.otrosReqs = otrosReqs;
	}

	public void setIdTramite(int idTramite) {
		this.idTramite = idTramite;
	}

	public String getDenomReq() {
		return denomReq;
	}

	public void setDenomReq(String denomReq) {
		this.denomReq = denomReq;
	}
	
	
}
