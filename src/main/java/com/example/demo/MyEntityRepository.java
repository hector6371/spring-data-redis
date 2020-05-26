package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyEntityRepository extends CrudRepository<MyEntity, Integer> {

    Optional<MyEntity> findByIndex1AndIndex2(int index1, int index2);

}
