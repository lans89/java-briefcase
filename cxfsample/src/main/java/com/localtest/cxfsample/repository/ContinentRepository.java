package com.localtest.cxfsample.repository;

import com.localtest.cxfsample.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Integer> {
}
