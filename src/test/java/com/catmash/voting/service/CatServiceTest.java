package com.catmash.voting.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.catmash.voting.CatmashVotingApplication;
import com.catmash.voting.data.entity.Cat;
import com.catmash.voting.data.repository.CatRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatmashVotingApplication.class)
public class CatServiceTest {

	@Autowired
	CatService catService;

	@Autowired
	CatRepository repository;

	@Test
	public void findCatUXFaceMashTest() {
		List<Cat> findCatUXFaceMash = catService.findCatUXFaceMash();
		assertNotNull(findCatUXFaceMash);
		assertEquals(2, findCatUXFaceMash.size(), " UX face mash should have 2 elements");
	}

	@Test
	public void voteTest() {
		String catId = "c8a";
		Cat catBeforeVote = repository.findById(catId);
		catService.vote(catId);
		Cat catAfterVote = repository.findById(catId);
		assertEquals(catBeforeVote.getScore() + 1, catAfterVote.getScore(), "");
	}

	@Test
	public void testJsonDataLoad() {
		Iterable<Cat> findAll = catService.findAll();
		assertNotNull("should be not null", findAll);
		long nbCat = StreamSupport.stream(findAll.spliterator(), false).count();
		assertTrue(nbCat >= 100, " should be equals to nb element of cat in file data/cats.json");
	}

}
