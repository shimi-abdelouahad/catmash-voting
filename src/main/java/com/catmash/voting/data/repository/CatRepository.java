package com.catmash.voting.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.catmash.voting.data.entity.Cat;

public interface CatRepository extends CrudRepository<Cat, Integer> {

	@Query(value = "SELECT *  FROM T_CAT  cat ORDER BY RAND() LIMIT 2", nativeQuery = true)
	List<Cat> findCatUXFaceMash();

	Cat findById(String id);

}
