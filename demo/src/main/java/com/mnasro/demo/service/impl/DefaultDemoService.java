package com.mnasro.demo.service.impl;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.mnasro.demo.exception.DemoException;
import com.mnasro.demo.model.DemoModel;
import com.mnasro.demo.repository.DemoRepository;
import com.mnasro.demo.service.DemoService;

@Service(value = "demoService")
public class DefaultDemoService implements DemoService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultDemoService.class);

	private static final String CLUSTERED_DATA_MODEL_BE_NULL = "demoModel cannot be null!";
	private static final String CLUSTERED_DATA_MODEL_ID_BE_NULL = "Id cannot be null!";

	@Autowired
	private DemoRepository demoRepository;

	@Override
	public DemoModel add(DemoModel demoModel) {
		Preconditions.checkNotNull(demoModel, CLUSTERED_DATA_MODEL_BE_NULL);
		demoModel.setId(null);

		LOG.info("start add Demo: fromCurrency [{}] , toCurrency [{}] , amount [{}] ,timestamp [{}] ",
				demoModel.getFromCurrency(), demoModel.getToCurrency(),
				demoModel.getAmount(), demoModel.getTimestamp());
		DemoModel saved = demoRepository.save(demoModel);
		LOG.info(
				"The Demo has been created successfully :id[{}] fromCurrency [{}] , toCurrency [{}] , amount [{}] ,timestamp [{}] ",
				saved.getId(), saved.getFromCurrency(), saved.getToCurrency(), saved.getAmount(), saved.getTimestamp());
		
		return saved;
	}

	@Transactional(noRollbackFor = {
			DemoException.class }, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public void addAll(Set<DemoModel> set) {
		for (DemoModel entity : set) {
			demoRepository.save(entity);
		}
	}

	@Override
	public Page<DemoModel> findAll(Pageable pageable) {
		
		
		pageable =pageable!=null? pageable:PageRequest.of(0, 10, Sort.by("fromCurrency").ascending());

		return demoRepository.findAll(pageable);
	}

	@Override
	public DemoModel findById(Integer id) {
		Preconditions.checkNotNull(id, CLUSTERED_DATA_MODEL_ID_BE_NULL);
 
		Optional<DemoModel> byId = demoRepository.findById(id);
		
		if(byId.isEmpty())
		{
			throw new DemoException("Demo for id not found");
		}
		
		return byId.get();
	}
}