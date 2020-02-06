package com.catmash.voting.service;

import java.util.List;

import com.catmash.voting.data.entity.Cat;

public interface CatService {

	void save(List<Cat> cats);

	List<Cat> findCatUXFaceMash();

	Iterable<Cat> findAll();

	void vote(String idVotedCat);

}
