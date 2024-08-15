package com.mnasro.demo.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mnasro.demo.dto.DemoDTO;
import com.mnasro.demo.dto.DemoListDTO;
import com.mnasro.demo.dto.PageDTO;

@SpringBootTest
class DemoFacadeTest {

	@Autowired
	@Qualifier("demoFacade")
	private DemoFacade demoFacade;
	
	private static final Set<DemoDTO>CLUSTERED_DATA_DTOS=new HashSet<>();
	
	static
	{
		CLUSTERED_DATA_DTOS.add(DemoDTO.builder().fromCurrency("JOD").toCurrency("USD").amount(10.0).timestamp(new Date()).build());
		CLUSTERED_DATA_DTOS.add(DemoDTO.builder().fromCurrency("JOD").toCurrency("SAR").amount(10.0).timestamp(new Date()).build());
	}

	
	@Test
	void testAdd() {
		DemoDTO demoDTO = demoFacade.add(CLUSTERED_DATA_DTOS.iterator().next());
		assertNotNull(demoDTO);

	}

	@Test
	void testAddAll() {
		demoFacade.addAll(DemoListDTO.builder().list(DemoFacadeTest.CLUSTERED_DATA_DTOS).build());
	}
	
	@Test
	void testfindAll() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("fromCurrency").ascending());

		PageDTO<DemoDTO> all = demoFacade.findAll(pageable);
		assertNotNull(all);

	}
	
	@Test
	void testfindId() {
		
		DemoDTO demoDTO = demoFacade.add(CLUSTERED_DATA_DTOS.iterator().next());
		
		DemoDTO byId = demoFacade.findById(demoDTO.getId());
		assertNotNull(byId);
		assertNotNull(byId.getId());

	}

}
