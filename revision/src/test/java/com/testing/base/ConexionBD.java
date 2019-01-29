package com.testing.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.testing.dataProvider.ConfigFileReader;
import com.testing.tool.cte.BaseCte;

public class ConexionBD {

	Connection conexion;
	static ConfigFileReader configFileReader;

	public static Connection conectar() {

		configFileReader = new ConfigFileReader();
		Connection conexion = null;

		try {
			Class.forName(BaseCte.BS_ORACLE_JDBC_DRIVER_ORACLE_DRIVER);
			String passwordBD = configFileReader.getPasswordBD();
			String userBD = configFileReader.getUserBD();
			String urlBD = configFileReader.getUrlBD();
			conexion = DriverManager.getConnection(urlBD, userBD, passwordBD);
			System.out.println("No se ha llegado al catch");
		} catch (ClassNotFoundException ex) {
			System.out.println("Error en la conexión de la base de datos.");
		} catch (SQLException e) {
			System.out.println("Error de tipo SqlException. No llega a la BD T-T");
			e.printStackTrace();
		}
		return conexion;
	}

}
