package com.catmash.voting.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.catmash.voting.api.model.CatApiBean;
import com.catmash.voting.api.model.CatUXFaceMashResponse;
import com.catmash.voting.data.entity.Cat;
import com.catmash.voting.exception.NbCatInsuffisabtException;
import com.catmash.voting.exception.PersistenceException;
import com.catmash.voting.service.CatService;

@RestController
@RequestMapping("/votes")
public class VoteController {

	@Autowired
	CatService catService;

	@GetMapping("generate")
	CatUXFaceMashResponse getCatUXFaceMash() throws Exception {
		List<Cat> uxCat = catService.findCatUXFaceMash();
		if (CollectionUtils.isEmpty(uxCat) || uxCat.size() == 1) {
			throw new NbCatInsuffisabtException("Can't Vote if not 2 elements");
		}
		ModelMapper mapper = new ModelMapper();
		CatUXFaceMashResponse response = new CatUXFaceMashResponse(mapper.map(uxCat.get(0), CatApiBean.class),
				mapper.map(uxCat.get(1), CatApiBean.class));

		return response;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postVote(@RequestBody CatApiBean catApi) throws PersistenceException {
		try {
			catService.vote(catApi.getId());
		} catch (Exception e) {
			throw new PersistenceException("Error", e);
		}
	}

	@ExceptionHandler(NbCatInsuffisabtException.class)
	public ResponseEntity<Object> invalidVoteExceptionHandler(NbCatInsuffisabtException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> persistenceException(PersistenceException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
