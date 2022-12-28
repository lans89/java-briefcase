package com.example.nosqlservice.repository;

import com.example.nosqlservice.model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {

    @Query(value = "{originCountry:'?0'}")
    public List<Brand> findAllByCountry(String country);
}
