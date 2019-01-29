package com.testing.tool;

import java.util.Locale;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.testing.tool.cte.BaseCte;

public class DataTool {

	protected static Locale locale = new Locale(BaseCte.BS_LOCALE_ES);
	protected static RandomService randomService = new RandomService();

	public static String generar(String regex) {
		FakeValuesService service = new FakeValuesService(locale, randomService);
		return service.regexify(regex);
	}

}
