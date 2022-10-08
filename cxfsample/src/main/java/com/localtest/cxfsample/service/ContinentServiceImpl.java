package com.localtest.cxfsample.service;

import com.localtest.cxfsample.endpoint.ContinentService;
import com.localtest.cxfsample.model.Continent;
import com.localtest.cxfsample.model.Country;
import com.localtest.cxfsample.repository.ContinentRepository;
import com.localtest.cxfsample.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    ContinentRepository continentRepository;
    @Autowired
    CountryRepository countryRepository;


    @Override
    public List<Continent> listAllContinent() {
        return continentRepository.findAll();
    }

    @Override
    public List<Country> listAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public List<Country> listAllCountryByContinentId(Integer idContinent) {
        return countryRepository.findAllByContinentId(idContinent);
    }
}
