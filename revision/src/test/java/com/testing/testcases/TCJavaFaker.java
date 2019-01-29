package com.testing.testcases;

import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.testing.tool.cte.BaseCte;
import com.testing.tool.cte.RegExpCte;

public class TCJavaFaker {

	public TCJavaFaker() {
		// TODO Auto-generated constructor stub
	}

	@Test
	public void probandoJavaFakerUno() {
		Locale locale = new Locale(BaseCte.BS_LOCALE_ES);
		RandomService randomService = new RandomService();
		FakeValuesService service = new FakeValuesService(locale, randomService);
		for (int i = 0; i < 10; i++) {
			String alphanumericString = service.regexify(RegExpCte.RGX_ACENTOS);
			Pattern compile = Pattern.compile(RegExpCte.RGX_ACENTOS);
			Matcher alphaMatcher = compile.matcher(alphanumericString);

			System.out.println(alphanumericString);

			boolean find = alphaMatcher.find();
			assertTrue(find);
		}
	}

}
