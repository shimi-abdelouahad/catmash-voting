package com.catmash.voting.api.service;

import java.util.Set;
import java.util.TreeSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catmash.voting.api.model.CatApiBean;
import com.catmash.voting.data.entity.Cat;
import com.catmash.voting.exception.PersistenceException;
import com.catmash.voting.service.CatService;

@RestController
@RequestMapping("/cats")
public class CatController {
	@Autowired
	CatService catService;

	@GetMapping()
	Set<CatApiBean> getAllScore() throws Exception {
		Iterable<Cat> all = catService.findAll();
		ModelMapper mapper = new ModelMapper();
		Set<CatApiBean> response = new TreeSet<CatApiBean>();
		all.forEach(e -> response.add(mapper.map(e, CatApiBean.class)));
		return response;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> persistenceException(PersistenceException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
