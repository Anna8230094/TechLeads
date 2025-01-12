package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Maria Spachou
 */
@WebMvcTest(RankingController.class)
public class RankingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRankingControl() throws Exception {
        this.mockMvc.perform(get("/hireandgo/home/ranking"))
                .andExpect(status().isOk()) // Ελέγχει ότι το status της απόκρισης είναι 200 (OK)
                .andExpect(view().name("ranking")) // Ελέγχει ότι επιστρέφεται το όνομα της όψης "ranking"
                ; // Ελέγχει ότι το attribute "message" είναι σωστό
    }
}