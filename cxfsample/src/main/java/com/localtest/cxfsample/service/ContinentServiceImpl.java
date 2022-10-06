package com.localtest.cxfsample.service;

import com.localtest.cxfsample.endpoint.ContinentService;
import com.localtest.cxfsample.model.Continent;
import com.localtest.cxfsample.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    ContinentRepository continentRepository;

    @Override
    public List<Continent> listAll() {
        return continentRepository.findAll();
    }
}
