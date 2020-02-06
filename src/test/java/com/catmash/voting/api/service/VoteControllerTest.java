package com.catmash.voting.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.catmash.voting.service.CatService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = VoteController.class)
public class VoteControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CatService catService;

	@Test
	public void retrieveUxFace() throws Exception {
		String exampleJson = "{\"id\":\"id1\",\"url\":\"http://test.url.cat1\",\"score\":0}";
		Mockito.doNothing().when(catService).vote(Mockito.anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cat/vote").accept(MediaType.APPLICATION_JSON)
				.content(exampleJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}
