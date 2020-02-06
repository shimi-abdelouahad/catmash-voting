package com.catmash.voting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catmash.voting.data.entity.Cat;
import com.catmash.voting.data.repository.CatRepository;

@Service
public class CatServiceImpl implements CatService {
	@Autowired
	CatRepository catRepository;

	@Override
	public void save(List<Cat> cats) {
		catRepository.saveAll(cats);
	}

	@Override
	public List<Cat> findCatUXFaceMash() {
		return catRepository.findCatUXFaceMash();
	}

	@Override
	public Iterable<Cat> findAll() {
		return catRepository.findAll();
	}

	@Override
	@Transactional
	public void vote(String idVotedCat) {
		Cat cat = catRepository.findById(idVotedCat);
		cat.setScore(cat.getScore() + 1);
		catRepository.save(cat);
	}
}
