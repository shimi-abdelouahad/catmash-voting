package com.catmash.voting.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.catmash.voting.data.entity.Cat;

public interface CatRepository extends CrudRepository<Cat, Integer> {

	Cat findById(String id);

}
