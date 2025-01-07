package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;
import com.example.demo.openai.service.OpenAiService;

/**
 * @author Maria Spachou
 */
@WebMvcTest(RegistrationFormController.class)
public class RegistrationFormControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private OpenAiService openAiService;

    @Test
    public void testRegistrationControl() throws Exception {
        mockMvc.perform(get("/hireandgo/home/registrationform"))
                .andExpect(status().isOk())
                .andExpect(view().name("registrationform"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testHandleRegistration() throws Exception {
        // Mock δεδομένα χρήστη
        Users user = new Users();
        user.setName("Maria");
        user.setField("Computer Science");
        user.setEmail("maria@example.com");

        // Mock αρχείο
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.txt", "text/plain", "Test content".getBytes());

        // Mock υπηρεσίες
        Mockito.doNothing().when(usersService).saveUsers(any(Users.class));
        Mockito.doNothing().when(openAiService).startRankingProcess(any(), any(Users.class));

        mockMvc.perform(multipart("/registrationform")
                        .file(mockFile)
                        .param("name", "Maria")
                        .param("field", "Computer Science")
                        .param("email", "maria@example.com")
                        .flashAttr("user", user))
                .andExpect(status().isOk())
                .andExpect(view().name("success"))
                .andExpect(model().attribute("username", "Maria"));

        // Επιβεβαίωση ότι οι υπηρεσίες καλούνται
        verify(usersService, times(1)).saveUsers(any(Users.class));
        verify(openAiService, times(1)).startRankingProcess(any(), any(Users.class));
    }
}

