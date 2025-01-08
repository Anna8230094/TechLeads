package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Maria Spachou
 */
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {
=======
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
>>>>>>> 281886951f0db5bbfb32722b5e6ad6a28e1cefce

	@Autowired
	private MockMvc mockMvc;

<<<<<<< HEAD
	@Test
	void homeTest() throws Exception {
		this.mockMvc.perform(get("/hireandgo/home/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("home")));
	}
=======
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHomeControl() throws Exception {
        // Εδώ εκτελούμε το αίτημα και ελέγχουμε την κατάσταση της απόκρισης και το view
        mockMvc.perform(get("/hireandgo/home/"))
               .andExpect(status().isOk())  // Ελέγχουμε ότι η κατάσταση της απόκρισης είναι 200 OK
               .andExpect(view().name("home"));  // Ελέγχουμε ότι το όνομα του view είναι "home"
    }
>>>>>>> 281886951f0db5bbfb32722b5e6ad6a28e1cefce
}
