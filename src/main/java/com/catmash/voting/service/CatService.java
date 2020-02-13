package com.catmash.voting.service;

import java.util.List;

import com.catmash.voting.data.entity.Cat;

public interface CatService {

	void save(List<Cat> cats) throws Exception;

	List<Cat> findCatUXFaceMash() throws Exception;

	Iterable<Cat> findAll() throws Exception;

	void vote(String idVotedCat) throws Exception;

}
