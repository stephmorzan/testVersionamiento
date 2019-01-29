package com.testing.testflow;

import java.util.Locale;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.javafaker.Number;
import com.testing.base.MetodosElementos;
import com.testing.tool.cte.BaseCte;

public class TFFormularioOcho {

	private static final String FORMULARIO_OCHO_JSP = "formularioOcho.jsp";
	private static final String INPUT_VALUE_MADRE = "input[value*='madre'";
	private static final String DIRECCION = "direccion";
	private static final String DNI2 = "dni";
	private static final String NOMBRE2 = "nombre";
	
	MetodosElementos metodos = new MetodosElementos();
	WebDriver driver;
	Logger miLogger;
	TFLogin login = new TFLogin();

	public TFFormularioOcho() {
		// TODO Auto-generated constructor stub
		this.driver = login.getDriver();
		this.miLogger = login.getMiLogger();
		setUp();
	}

	public void setUp() {
		login.loginOk();
		Navigation navigate = driver.navigate();
		navigate.to(BaseCte.BS_HTTP_LOCALHOST_8081_MINCETUR + TFFormularioOcho.FORMULARIO_OCHO_JSP);
	}

	public boolean llenarFormularioOcho() {
		Locale locale = new Locale(BaseCte.BS_LOCALE_ES);
		Faker faker = new Faker(locale);
		By id = By.id(NOMBRE2);
		WebElement nombre = driver.findElement(id);
		Name name = faker.name();
		String fullName = name.fullName();
		nombre.sendKeys(fullName);// aqui genero un nombre cualquiera
		By id2 = By.id(DNI2);
		WebElement dni = driver.findElement(id2);
		Number number = faker.number();
		String digits = number.digits(8);
		dni.sendKeys(digits);// aqui genero un dni aleatorio
		By id3 = By.id(DIRECCION);
		WebElement findElement = driver.findElement(id3);
		Address address = faker.address();
		String fullAddress = address.fullAddress();
		findElement.sendKeys(fullAddress);
		By cssSelector = By.cssSelector(INPUT_VALUE_MADRE);
		WebElement findElement2 = driver.findElement(cssSelector);
		findElement2.click();
		// driver.findElement(By.id("btn-guardar-declaracion")).click();
		return true;
	}

}
