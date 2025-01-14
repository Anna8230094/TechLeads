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