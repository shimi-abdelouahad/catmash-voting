package com.catmash.voting.api.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catmash.voting.api.model.CatApiBean;
import com.catmash.voting.api.model.CatUXFaceMashResponse;
import com.catmash.voting.data.entity.Cat;
import com.catmash.voting.exception.NbCatInsuffisabtException;
import com.catmash.voting.service.CatService;

@RestController
@RequestMapping("/cats")
public class CatController {
	@Autowired
	CatService catService;

	@GetMapping("/catsUXFaceMash")
	CatUXFaceMashResponse getCatUXFaceMash() throws NbCatInsuffisabtException {

		List<Cat> uxCat = catService.findCatUXFaceMash();
		if (CollectionUtils.isEmpty(uxCat) || uxCat.size() == 1) {
			throw new NbCatInsuffisabtException("Can't Vote if not 2 elements");
		}
		ModelMapper mapper = new ModelMapper();
		CatUXFaceMashResponse response = new CatUXFaceMashResponse(mapper.map(uxCat.get(0), CatApiBean.class),
				mapper.map(uxCat.get(1), CatApiBean.class));

		return response;
	}

	@GetMapping("/catsAllScore")
	Set<CatApiBean> getAllScore() {
		Iterable<Cat> all = catService.findAll();
		ModelMapper mapper = new ModelMapper();
		Set<CatApiBean> response = new TreeSet<CatApiBean>();
		all.forEach(e -> response.add(mapper.map(e, CatApiBean.class)));
		return response;
	}

	@ExceptionHandler(NbCatInsuffisabtException.class)
	public ResponseEntity<Object> invalidVoteExceptionHandler(NbCatInsuffisabtException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
	}

}
