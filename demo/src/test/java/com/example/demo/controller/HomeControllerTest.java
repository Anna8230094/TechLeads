package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HomeController homeController;

    @Test
    public String testHomeControl() throws Exception {
        when(homeController.homeControl()).thenReturn(testHomeControl());
        // Εδώ εκτελούμε το αίτημα και ελέγχουμε την κατάσταση της απόκρισης και το view
        mockMvc.perform(get("/hireandgo/home/"))
               .andExpect(status().isOk())  // Ελέγχουμε ότι η κατάσταση της απόκρισης είναι 200 OK
               .andExpect(view().name("home"));  // Ελέγχουμε ότι το όνομα του view είναι "home"
                       return null;
    }
}

