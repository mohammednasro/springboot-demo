package com.mnasro.demo.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mnasro.demo.dto.DemoDTO;
import com.mnasro.demo.dto.DemoListDTO;
import com.mnasro.demo.util.CurrencyUtil;

@Component
public class DemoListDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DemoListDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		DemoListDTO dto = (DemoListDTO) target;

		if (dto == null || CollectionUtils.isEmpty(dto.getList())) {
			errors.rejectValue("list", "Invalid list", "Invalid list");
			return;
		}

		for (DemoDTO demoDTO : dto.getList()) {
			if (!CurrencyUtil.isValid(demoDTO.getFromCurrency(), demoDTO.getToCurrency())) {
				errors.rejectValue("list", "Invalid list", "Invalid currencies list ");
				return;
			}
		}
	}

}