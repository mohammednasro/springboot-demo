package com.mnasro.demo.facade;

import org.springframework.data.domain.Pageable;

import com.mnasro.demo.dto.PageDTO;
import com.mnasro.demo.dto.DemoDTO;
import com.mnasro.demo.dto.DemoListDTO;


public interface DemoFacade {

	public DemoDTO add(DemoDTO demoDTO);


	public void addAll(DemoListDTO demoListDTO);
	

    public PageDTO<DemoDTO> findAll(Pageable pageable);
    

    public DemoDTO findById(Integer id);


}
