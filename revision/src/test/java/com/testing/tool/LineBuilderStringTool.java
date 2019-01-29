package com.testing.tool;

public class LineBuilderStringTool {

	private StringBuilder sb;

	public static String saltoDeLinea() {
		StringBuilder sb = new StringBuilder();
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}

	public LineBuilderStringTool() {
		sb = new StringBuilder();
	}

	public void append(String str) {
		sb.append(str != null ? str : "");
	}

	public void appendLine(String str) {
		sb.append(str != null ? str : "").append(System.getProperty("line.separator"));
	}

	public void appendSalto() {
		sb.append(System.getProperty("line.separator"));
	}

	public String toString() {
		return sb.toString();
	}

}