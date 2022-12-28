package com.localpany.reactiveservice.repository;

import com.localpany.reactiveservice.model.WordVerb;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordVerbRepository extends PagingAndSortingRepository<WordVerb, Long> {
}
