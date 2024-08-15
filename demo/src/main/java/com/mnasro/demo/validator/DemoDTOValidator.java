package com.mnasro.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mnasro.demo.dto.DemoDTO;
import com.mnasro.demo.util.CurrencyUtil;

@Component
public class DemoDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DemoDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DemoDTO dto = (DemoDTO) target;

		if (!CurrencyUtil.isValid(dto.getFromCurrency(), dto.getToCurrency())) {
			errors.rejectValue("fromCurrency", "Invalid fromCurrency", "Invalid fromCurrency");
			errors.rejectValue("toCurrency", "Invalid toCurrency", "Invalid toCurrency");
		}

	}

}