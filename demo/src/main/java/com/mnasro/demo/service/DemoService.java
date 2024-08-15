package com.mnasro.demo.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mnasro.demo.model.DemoModel;


public interface DemoService {	
	

	public DemoModel add(DemoModel demoModel);
   

    public void addAll(Set<DemoModel> list);
    

    public Page<DemoModel> findAll(Pageable pageable);
    

    public DemoModel findById(Integer id);

}
