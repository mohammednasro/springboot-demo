package com.mnasro.demo.mapper;

import org.mapstruct.Mapper;

import com.mnasro.demo.dto.DemoDTO;
import com.mnasro.demo.model.DemoModel;

@Mapper(componentModel = "spring")
public interface DemoMapper {

//	@Mapping(source = "id", target = "id")
//	@Mapping(source = "fromCurrency", target = "fromCurrency")
//	@Mapping(source = "toCurrency", target = "toCurrency")
//	@Mapping(source = "timestamp", target = "timestamp")
//	@Mapping(source = "amount", target = "amount")
	public DemoModel toEntity(DemoDTO dto);
	
//	@Mapping(source = "id", target = "id")
//	@Mapping(source = "fromCurrency", target = "fromCurrency")
//	@Mapping(source = "toCurrency", target = "toCurrency")
//	@Mapping(source = "timestamp", target = "timestamp")
//	@Mapping(source = "amount", target = "amount")
	public DemoDTO toDTO(DemoModel entity);
 
}
