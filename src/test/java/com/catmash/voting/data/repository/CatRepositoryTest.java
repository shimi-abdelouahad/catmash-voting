package com.catmash.voting.data.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.catmash.voting.CatmashVotingApplication;
import com.catmash.voting.data.entity.Cat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatmashVotingApplication.class)
public class CatRepositoryTest {

	@Autowired
	CatRepository catRepository;

	@Test
	public void testSave() {
		Cat entity = new Cat();
		entity.setId("idcat");
		entity.setUrl("http://test.url.cat");
		entity.setScore(1);
		catRepository.save(entity);
		assertNotNull("should be not null", catRepository.findById(1));
	}

	@Test
	public void testSaveAll() {
		Cat cat1 = new Cat();
		cat1.setId("id1");
		cat1.setUrl("http://test.url.cat1");

		Cat cat2 = new Cat();
		cat2.setId("id2");
		cat2.setUrl("http://test.url.cat2");

		Cat cat3 = new Cat();
		cat3.setId("id3");
		cat3.setUrl("http://test.url.cat2");

		catRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		assertNotNull("should be not null", catRepository.findById("id1"));
		assertNotNull("should be not null", catRepository.findById("id2"));
		assertNotNull("should be not null", catRepository.findById("id3"));
	}

}
