package com.home.service.repository;

import com.home.service.model.WordMeaning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAWordMeaning extends JpaRepository<WordMeaning, Long> {
}
