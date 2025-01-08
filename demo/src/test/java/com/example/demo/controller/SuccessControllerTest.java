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
class SuccessControllerTest {
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
	void successTest() throws Exception {
		this.mockMvc.perform(get("/hireandgo/home/registrationform/success/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("success")));
	}
}
=======
@WebMvcTest(SuccessController.class)
public class SuccessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSuccessControl() throws Exception {
        // Εδώ εκτελούμε το αίτημα και ελέγχουμε την κατάσταση της απόκρισης και το view
        mockMvc.perform(get("/hireandgo/home/registrationform/success/1111"))
               .andExpect(status().isOk())  // Ελέγχουμε ότι η κατάσταση της απόκρισης είναι 200 OK
               .andExpect(view().name("success"));  // Ελέγχουμε ότι το όνομα του view είναι "home"
    }
}
>>>>>>> 281886951f0db5bbfb32722b5e6ad6a28e1cefce
