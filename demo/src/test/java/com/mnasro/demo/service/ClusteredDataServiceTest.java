package com.mnasro.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mnasro.demo.model.DemoModel;
@SpringBootTest
class DemoServiceTest {

	@Autowired
	@Qualifier("demoService")
	private DemoService demoService;
	
	private static final Set<DemoModel>CLUSTERED_DATA_MODELS=new HashSet<>();
	
	static
	{
		CLUSTERED_DATA_MODELS.add(DemoModel.builder().fromCurrency("JOD").toCurrency("USD").amount(10.0).timestamp(new Date()).build());
		CLUSTERED_DATA_MODELS.add(DemoModel.builder().fromCurrency("JOD").toCurrency("SAR").amount(10.0).timestamp(new Date()).build());
	}

	@Test
	void testAdd() {
		DemoModel demoModel = demoService.add(CLUSTERED_DATA_MODELS.iterator().next());
		assertNotNull(demoModel);
		assertNotNull(demoModel.getId());
	}


	@Test
	void testAddAll() {
		demoService.addAll(CLUSTERED_DATA_MODELS);

	}

    
	@Test
	void testfindAll() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("fromCurrency").ascending());

		Page<DemoModel> all = demoService.findAll(pageable);
		assertNotNull(all);
	}
	
	@Test
	void testfindId() {
		
		DemoModel demoModel = demoService.add(CLUSTERED_DATA_MODELS.iterator().next());
		
		DemoModel byId = demoService.findById(demoModel.getId());
		assertNotNull(byId);
		assertNotNull(byId.getId());


	}
}
