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


@WebMvcTest(SuccessController.class)
public class SuccessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuccessController successController;

    @Test
    public String testSuccessControl() throws Exception {
        when(successController.handleSuccess(null, null, null)).thenReturn(testSuccessControl());
        // Εδώ εκτελούμε το αίτημα και ελέγχουμε την κατάσταση της απόκρισης και το view
        mockMvc.perform(get("/hireandgo/home/"))
               .andExpect(status().isOk())  // Ελέγχουμε ότι η κατάσταση της απόκρισης είναι 200 OK
               .andExpect(view().name("home"));  // Ελέγχουμε ότι το όνομα του view είναι "home"
                       return null;
    }
}
