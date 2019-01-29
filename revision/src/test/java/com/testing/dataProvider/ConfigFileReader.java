package com.testing.dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.PropertyCte;

/**FIXME EST automatizar la carpeta de base */
/**FIXME categorizar, mediante ´.´ el nombre de las propiedades del archivo properties */
public class ConfigFileReader {
		
	private Properties properties;
	private final String propertyFilePath = BaseCte.BS_RESOURCES_FOLDER + BaseCte.BS_CONFIG_PROPERTIES;
	
	public ConfigFileReader() {
		// TODO Auto-generated constructor stub
		BufferedReader reader;
		
		try {
			FileReader in = new FileReader(propertyFilePath);
			reader = new BufferedReader(in);
			properties = new Properties();
			
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("config.properties no se encuentra en " + propertyFilePath);
			
		}
	}
	
	//Getters de cada elemento de config.properties

	public String getBaseUrl() {
		String resultado = properties.getProperty(PropertyCte.PR_URL_BASE);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("Url no esta especificado en archivo config.properties");
		}
	}
	
	public String getPerfilDriver() {
		String resultado = properties.getProperty(PropertyCte.PR_PERFIL_DRIVER);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("El perfil para el browser no esta especificado en archivo config.properties");
		}
	}
	
	public String getUrlBD() {
		String resultado = properties.getProperty(PropertyCte.PR_URL_BD);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("La URL para conectar a la BD no esta especificado en archivo config.properties");
		}
	}
	
	public String getUserBD() {
		String resultado = properties.getProperty(PropertyCte.PR_USER_BD);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("El usuario para conectar a BD no esta especificado en archivo config.properties");
		}
	}
	
	public String getPasswordBD() {
		String resultado = properties.getProperty(PropertyCte.PR_CLAVE_BD);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("La clave para conectar a BD no esta especificado en archivo config.properties");
		}
	}
	
	public String getLoginUser() {
		String resultado = properties.getProperty(PropertyCte.PR_LOGIN_USER_OK);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("El usuario para login no esta especificado en archivo config.properties");
		}
	}
	
	public String getLoginPassword() {
		String resultado = properties.getProperty(PropertyCte.PR_LOGIN_CLAVE_OK);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("La clave para login no esta especificado en archivo config.properties");
		}
	}
	
	public int getAleatorio() {
		String txt = properties.getProperty(PropertyCte.PR_MAX_VALS_COMBO_BOX);
		int resultado = Integer.parseInt(txt);
		if (resultado != 0) {
			return resultado;
		}else {
			throw new RuntimeException("El valor del nro aleatorio no esta especificado en archivo config.properties");
		}
	}
	
	public int getAleatorioOrdenComboBox() {
		String txt = properties.getProperty(PropertyCte.PR_RANDOM_ORDEN_COMBO);
		int resultado = Integer.parseInt(txt);
		if (resultado != 0) {
			return resultado;
		}else {
			throw new RuntimeException("El nro random para Orden en el ComboBox no esta especificado en archivo config.properties");
		}
	}
	
	public int getValorLongTextoReq() {
		String txt = properties.getProperty(PropertyCte.PR_LONG_TEXTO_REQ);
		int resultado = Integer.parseInt(txt);
		if (resultado != 0) {
			return resultado;
		}else {
			throw new RuntimeException("El valor de la longitud del texto del requerimiento no esta especificado en archivo config.properties");
		}
	}
	
	public String getRegexInputCharacters() {
		String resultado = properties.getProperty(PropertyCte.PR_REGEX_INPUT_CARACTERES);
		if (resultado != null) {
			return resultado;
		}else {
			throw new RuntimeException("El valor del regex para todos los caracteres en los inputs no esta especificado en archivo config.properties");
		}
	}
	
	public int getRegexQuantifyInputCharacters() {
		String txt = properties.getProperty(PropertyCte.PR_REGEX_CANT_INPUT_CARACTERES);
		int resultado = Integer.parseInt(txt);
		if (resultado != 0) {
			return resultado;
		}else {
			throw new RuntimeException("El valor de la cantidad de caracteres en el regex de los inputs no esta especificado en archivo config.properties");
		}
	}
	
	//Getters y Setters propios de la clase
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getPropertyFilePath() {
		return propertyFilePath;
	}
	
	

}
