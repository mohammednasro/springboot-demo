package com.mnasro.demo.facade.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Preconditions;
import com.mnasro.demo.dto.PageDTO;
import com.mnasro.demo.dto.DemoDTO;
import com.mnasro.demo.dto.DemoListDTO;
import com.mnasro.demo.facade.DemoFacade;
import com.mnasro.demo.mapper.DemoMapper;
import com.mnasro.demo.model.DemoModel;
import com.mnasro.demo.service.DemoService;


@Service(value = "demoFacade")
public class DefaultDemoFacade implements DemoFacade {

	private static final String CLUSTERED_DATA_DTO_BE_NULL = "demoDTO cannot be null!";
	private static final String CLUSTERED_DATA_DTO_ID_BE_NULL = "Id cannot be null!";

	private static final String CLUSTERED_DATA_LIST_DTO_BE_NULL = "demoListDTO cannot be null or empty!";

	@Autowired
	@Qualifier("demoService")
	private DemoService demoService;

	@Autowired
	private DemoMapper demoMapper;

	@Override
	public DemoDTO add(DemoDTO demoDTO) {
		Preconditions.checkNotNull(demoDTO, CLUSTERED_DATA_DTO_BE_NULL);

		return demoMapper.toDTO(demoService.add(demoMapper.toEntity(demoDTO)));
	}

	@Override
	public void addAll(DemoListDTO demoListDTO) {
		Preconditions.checkArgument(
				demoListDTO != null && !CollectionUtils.isEmpty(demoListDTO.getList()),
				CLUSTERED_DATA_LIST_DTO_BE_NULL);

		Set<DemoModel> set = new HashSet<>();

		for (DemoDTO dto : demoListDTO.getList()) {
			set.add(demoMapper.toEntity(dto));
		}

		demoService.addAll(set);
	}

	@Override
	public PageDTO<DemoDTO> findAll(Pageable pageable) {
		Page<DemoModel> all = demoService.findAll(pageable);
		
		
		List<DemoDTO> list=new ArrayList<>();
		for (DemoModel model : all.toList()) {
			list.add(demoMapper.toDTO(model));
		}
		
		return new PageDTO<DemoDTO>(list,all.getNumber(),all.getSize(),all.getTotalElements(),all.getTotalPages());
	}

	@Override
	public DemoDTO findById(Integer id) {
		Preconditions.checkNotNull(id, CLUSTERED_DATA_DTO_ID_BE_NULL);
		
		return demoMapper.toDTO( demoService.findById(id));
	}
}
