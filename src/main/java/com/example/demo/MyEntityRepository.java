package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyEntityRepository extends CrudRepository<MyEntity, Integer> {

    Optional<MyEntity> findByIndex1(int index1);

}
