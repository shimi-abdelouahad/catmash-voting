package com.catmash.voting.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.catmash.voting.api.model.CatApiBean;
import com.catmash.voting.exception.PersistenceException;
import com.catmash.voting.service.CatService;

@RestController
@RequestMapping("/cat")
public class VoteController {

	@Autowired
	CatService catService;

	@PostMapping("/vote")
	@ResponseStatus(HttpStatus.CREATED)
	public void postVote(@RequestBody CatApiBean catApi) {
		catService.vote(catApi.getId());
	}

	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<Object> persistenceException(PersistenceException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
