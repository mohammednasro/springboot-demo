package com.mnasro.demo.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mnasro.demo.model.DemoModel;

@Repository
public interface DemoRepository extends JpaRepository<DemoModel, Integer> {

	public Page<DemoModel> findAll(Pageable pageable);
}