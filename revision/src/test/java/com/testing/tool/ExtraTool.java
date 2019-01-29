package com.testing.tool;

import java.util.Random;

import com.testing.dataProvider.ConfigFileReader;

public class ExtraTool {

	ConfigFileReader config;
	
	public ExtraTool() {
		// TODO Auto-generated constructor stub
		config = new ConfigFileReader();
	}
	
	public String generarMuchosCaracteres() {
		int i = 0;
		//String etiqueta = "brerbsdgvswkemflkwemflkmwelkul";
		String etiqueta = "brerbsdgvswkemflkwemflkmwelkul"; //tiene 30 caracteres
		do {
			etiqueta = etiqueta+"vwefkjwefoiwejfoijop"; //tiene 20 caracteres
			i++;
		}while (i<10);
		return etiqueta;
	}
	
	public int cantValoresCombo() {
		int aleatorio = config.getAleatorio();
		return new Random().nextInt(aleatorio); //se daran entre 0 a 10 clicks
	}
	
	public String devolverValorParaCombo(/*int cantValores*/) {
		//String[] valores = new String[30];
		
		return "";
	}
	
}
