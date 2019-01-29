package com.testing.tool;

public class ReflectionTool {

	public ReflectionTool() {
		// TODO Auto-generated constructor stub
	}
	
	public static String gettearNombreMetodoActual() {
		final StackTraceElement element = Thread.currentThread().getStackTrace()[2];
		final String nombClase = element.getClassName();
		return nombClase.substring(nombClase.lastIndexOf('.') + 1, nombClase.length()) + " ==> " + element.getMethodName();
	}

}
