package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Maria Spachou
 */
@SpringBootTest
@AutoConfigureMockMvc
class SuccessControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void successTest() throws Exception {
		this.mockMvc.perform(get("/hireandgo/home/registrationform/success/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
	}
}