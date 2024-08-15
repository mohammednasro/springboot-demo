package com.mnasro.demo.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mnasro.demo.model.DemoModel;
import com.mnasro.demo.service.DemoService;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

	@Autowired
	private MockMvc mockMvc;

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
	void testAdd() throws Exception {
		String requestBody = getDemoDTORequestBody();
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/clustered-data/add").contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testAddAll() throws Exception {
		String requestBody = getDemoListDTORequestBody();
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/clustered-data/add/all")
				.contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	void testFindAll() throws Exception {
		
		 mockMvc.perform(MockMvcRequestBuilders.get("/v1/clustered-data")
	                .param("page", "0")
	                .param("size", "10")
	                .param("sortBy", "fromCurrency")
	                .param("direction", "asc")
	                .header("accept", "*/*"))
	                .andExpect(MockMvcResultMatchers.status().isOk());
//	
	}
	
	
	@Test
	void testFindById() throws Exception {

		DemoModel demoModel = demoService.add(CLUSTERED_DATA_MODELS.iterator().next());
		
		DemoModel byId = demoService.findById(demoModel.getId());
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/clustered-data/{id}",byId.getId())
                .header("accept", "*/*"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private String getDemoDTORequestBody() {
		String requestBody = "{ \"fromCurrency\": \"JOD\", \"toCurrency\": \"USD\", \"timestamp\": \"2024-06-05T08:43:26.735Z\", \"amount\": 10 }";
																																								
		return requestBody;
	}

	private String getDemoListDTORequestBody() {
		String requestBody = "{  \"list\": [    {      \"fromCurrency\": \"JOD\",      \"toCurrency\": \"USD\",      \"timestamp\": \"2024-06-05T09:24:24.716Z\",      \"amount\": 10    }    ,    {      \"fromCurrency\": \"JOD\",      \"toCurrency\": \"SAR\",      \"timestamp\": \"2024-06-05T09:24:24.716Z\",      \"amount\": 20    }  ]}";
		return requestBody;
	}

}
