package com.mnasro.demo.util;
import java.util.Currency;

import org.apache.commons.lang3.StringUtils;

public class CurrencyUtil {

	private CurrencyUtil() {
	}

	public static boolean isValid(String fromCurrency, String toCurrency) {

		return isValid(fromCurrency) && isValid(toCurrency) && !fromCurrency.equals(toCurrency);
	}

	public static boolean isValid(String isocode) {
		if (StringUtils.isBlank(isocode)) {
			return false;
		}

		try {
			return Currency.getInstance(isocode) != null;
		} catch (Exception e) {
			return false;
		}
	}

}