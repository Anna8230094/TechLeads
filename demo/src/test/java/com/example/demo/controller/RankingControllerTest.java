package com.example.demo.controller;

<<<<<<< HEAD
import static org.hamcrest.Matchers.containsString;
=======
>>>>>>> 281886951f0db5bbfb32722b5e6ad6a28e1cefce
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
=======
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
>>>>>>> 281886951f0db5bbfb32722b5e6ad6a28e1cefce
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Maria Spachou
 */
@SpringBootTest
@AutoConfigureMockMvc
class RankingControllerTest {

	@Autowired
	private MockMvc mockMvc;

<<<<<<< HEAD
	@Test
	void rankingTest() throws Exception {
		this.mockMvc.perform(get("/hireandgo/home/ranking"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ranking")));
	}
=======
    @Test
    void testRankingControl() throws Exception {
        this.mockMvc.perform(get("/hireandgo/home/ranking"))
                .andExpect(status().isOk()) // Ελέγχει ότι το status της απόκρισης είναι 200 (OK)
                .andExpect(view().name("ranking")) // Ελέγχει ότι επιστρέφεται το όνομα της όψης "ranking"
                .andExpect(model().attribute("message", "Please enter your email")); // Ελέγχει ότι το attribute "message" είναι σωστό
    }
>>>>>>> 281886951f0db5bbfb32722b5e6ad6a28e1cefce
}

