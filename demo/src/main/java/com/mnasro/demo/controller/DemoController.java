package com.mnasro.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mnasro.demo.dto.PageDTO;
import com.mnasro.demo.dto.DemoDTO;
import com.mnasro.demo.dto.DemoListDTO;
import com.mnasro.demo.exception.WebserviceValidationException;
import com.mnasro.demo.facade.DemoFacade;
import com.mnasro.demo.validator.DemoDTOValidator;
import com.mnasro.demo.validator.DemoListDTOValidator;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/demo")
@Tag(name = "Demo")
public class DemoController {

	@Autowired
	@Qualifier("demoFacade")
	private DemoFacade demoFacade;

	@Autowired
	private DemoDTOValidator demoDTOValidator;

	@Autowired
	private DemoListDTOValidator demoListDTOValidator;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody @Valid DemoDTO demoDTO) {

		Errors errors = demoDTOValidator.validateObject(demoDTO);

		if (errors.hasErrors()) {
			throw new WebserviceValidationException(errors);
		}

		demoFacade.add(demoDTO);
	}

	@PostMapping("/add/all")
	@ResponseStatus(HttpStatus.CREATED)
	public void addAll(@RequestBody @Valid DemoListDTO demoListDTO) {

		Errors errors = demoListDTOValidator.validateObject(demoListDTO);

		if (errors.hasErrors()) {
			throw new WebserviceValidationException(errors);
		}

		demoFacade.addAll(demoListDTO);
	}
	
    @GetMapping("/{id}")
	public DemoDTO getById(@PathVariable int id) {

		return demoFacade.findById(id);
	}
    
    @GetMapping
  	public PageDTO<DemoDTO> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "fromCurrency") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

    	Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

  		return demoFacade.findAll(pageable);
  	}
}
