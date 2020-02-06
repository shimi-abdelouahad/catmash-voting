package com.catmash.voting;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.catmash.voting.data.entity.Cat;
import com.catmash.voting.service.CatService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class CatmashVotingApplication {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(CatmashVotingApplication.class, args);
	}

	/**
	 * Read json data and write in bdd
	 * 
	 * @param catService
	 * @return
	 */
	@Bean
	CommandLineRunner runner(CatService catService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Cat>> typeReference = new TypeReference<List<Cat>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/data/cats.json");
			List<Cat> cats = mapper.readValue(inputStream, typeReference);
			logger.info("Cats add into data base:: ", cats);
			catService.save(cats);
		};
	}

}
