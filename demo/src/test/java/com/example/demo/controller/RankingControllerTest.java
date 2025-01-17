/*
 * Copyright [2024-2025] [TechLeads]
 *
 * Licensed under multiple licenses:
 * 1. Apache License, Version 2.0 (the «Apache License»);
 *    You may obtain a copy of the Apache License at:
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * 2. MIT License (the «MIT License»);
 *    You may obtain a copy of the MIT License at:
 *        https://opensource.org/licenses/MIT
 *
 * 3. Eclipse Public License 2.0 (the «EPL 2.0»);
 *    You may obtain a copy of the EPL 2.0 at:
 *        https://www.eclipse.org/legal/epl-2.0/
 *
 * You may not use this file except in compliance with one or more of these licenses.
 * Unless required by applicable law or agreed to in writing, software distributed
 * under these licenses is distributed on an «AS IS» BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied.
 * See the applicable licenses for the specific language governing permissions and
 * limitations under those licenses.
 */
package com.example.demo.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.database.ranking.RankingRepository;
import com.example.demo.database.ranking.RankingResult;

/**
 * @author Maria Spachou
 */
@WebMvcTest(RankingController.class)
public class RankingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RankingRepository rankingRepository; // Μπορείς να χρησιμοποιήσεις @MockBean για να κάνεις mock το RankingRepository

    @Test
    void testRankingControl() throws Exception {
        String id = "123";  // Εδώ το id που θα χρησιμοποιήσεις για το mock

        // Δημιουργία mock δεδομένων
        RankingResult rankingResult = new RankingResult("Resume1", "Summary of resume"); // Δημιουργία ενός mock αντικειμένου RankingResult
        rankingResult.setIdRanking(1L); // Ρύθμιση του ID για το RankingResult
        rankingResult.setSessionId(id);
        when(rankingRepository.findBySessionId(id)).thenReturn(Collections.singletonList(rankingResult));

        // Δημιουργία της αναμενόμενης JSON μορφής ως string
        String expectedJsonString = "[{\"resume\":\"Resume1\",\"idRanking\":1,\"sessionId\":\"123\",\"resumeSummary\":\"Summary of resume\"}]";

            
        this.mockMvc.perform(get("/hireandgo/home/ranking/{id}", id))
                .andExpect(status().isOk()) // Ελέγχει ότι το status της απόκρισης είναι 200 (OK)
                .andExpect(view().name("ranking")) // Ελέγχει ότι επιστρέφεται το όνομα της όψης "ranking"
                .andExpect(model().attributeExists("items"))  // Ελέγχει ότι το μοντέλο έχει το attribute "items"
                .andExpect(model().attribute("items", expectedJsonString));

        // Επαλήθευση ότι το repository καλείται με το σωστό sessionId
        verify(rankingRepository).findBySessionId(id);
    }
}