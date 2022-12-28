package com.example.nosqlservice.repository;

import com.example.nosqlservice.model.HttpRequestRegister;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpRequestRegisterRepository extends MongoRepository<HttpRequestRegister, String> {

}
