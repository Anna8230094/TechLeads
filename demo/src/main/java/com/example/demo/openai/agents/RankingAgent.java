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
package com.example.demo.openai.agents;

import org.springframework.stereotype.Service;

@Service
public class RankingAgent extends OpenAiAssistant {

    public static final String INSTRUCTIONS = "You are responsible for a cv ranking procedure where other agents are part of as well. Your role is to receive a list of resumes in a csv format and return the applicant’s resumes ranked from most suitable to least suitable and return to me the id's of ranking cvs.";
    public static final String MODEL = "gpt-4o-mini";
    public static final String NAME = "Ranking Applicants";

    {
        instructions = INSTRUCTIONS;
        model = MODEL;
        name = NAME;
    }

}
