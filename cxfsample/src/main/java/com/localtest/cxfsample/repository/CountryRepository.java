package com.localtest.cxfsample.repository;

import com.localtest.cxfsample.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    List<Country> findAllByContinentId(Integer idContinent);
}
