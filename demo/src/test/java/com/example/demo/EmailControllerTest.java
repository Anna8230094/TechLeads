/*package com.example.demo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)

public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        Mockito.reset(emailService);
    }

    @Test
    public void testSendEmailSuccess() throws Exception {
        // Mock the EmailService behavior
        Mockito.doNothing().when(emailService).sendEmail(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());

        // Perform the request and validate the response
        mockMvc.perform(get("/send-email")
                .param("to", "test@example.com")
                .param("subject", "Test Subject")
                .param("body", "Test Body"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent successfully!"));

        // Verify the interaction with the service
        Mockito.verify(emailService).sendEmail("test@example.com", "Test Subject", "Test Body");
    }

    @Test
    public void testSendEmailInvalidInput() throws Exception {
        // Test missing required parameters
        mockMvc.perform(get("/send-email")
                .param("subject", "Test Subject")
                .param("body", "Test Body"))
                .andExpect(status().isBadRequest());
    }
}
*/
