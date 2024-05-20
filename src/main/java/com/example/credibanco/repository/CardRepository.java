package com.example.credibanco.repository;

import com.example.credibanco.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {

    //Card findByIdAndRandom(Integer id, Integer random);
}
