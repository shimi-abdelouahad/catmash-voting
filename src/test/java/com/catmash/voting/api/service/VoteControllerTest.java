package com.catmash.voting.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
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

import com.catmash.voting.data.entity.Cat;
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
		Cat cat1 = new Cat();
		cat1.setId("id1");
		cat1.setUrl("http://test.url.cat1");

		Cat cat2 = new Cat();
		cat2.setId("id2");
		cat2.setUrl("http://test.url.cat2");
		List<Cat> catsMock = Arrays.asList(cat1, cat2);
		Mockito.when(catService.findCatUXFaceMash()).thenReturn(catsMock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/votes/generate")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "{\"first\":{\"id\":\"id1\",\"url\":\"http://test.url.cat1\",\"score\":0},\"seconde\":{\"id\":\"id2\",\"url\":\"http://test.url.cat2\",\"score\":0}}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void retrieveUxFace_should_raise_exception_when_size_1() throws Exception {
		Cat cat1 = new Cat();
		cat1.setId("id1");
		cat1.setUrl("http://test.url.cat1");

		List<Cat> catsMock = Arrays.asList(cat1);
		Mockito.when(catService.findCatUXFaceMash()).thenReturn(catsMock);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/votes/generate")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.NOT_IMPLEMENTED.value(), result.getResponse().getStatus());

	}

	@Test
	public void vote() throws Exception {
		String exampleJson = "{\"id\":\"id1\",\"url\":\"http://test.url.cat1\",\"score\":0}";
		Mockito.doNothing().when(catService).vote(Mockito.anyString());
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/votes").accept(MediaType.APPLICATION_JSON)
				.content(exampleJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

	}

}
