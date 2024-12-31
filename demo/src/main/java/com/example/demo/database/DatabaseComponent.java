package com.example.demo.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.database.ranking.RankingResult;
import com.example.demo.database.ranking.RankingService;
import com.example.demo.database.researcher.ResearcherResult;
import com.example.demo.database.researcher.ResearcherService;
import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;

@Component
public class DatabaseComponent implements CommandLineRunner {
    @Autowired
    UsersService usersService;
    @Autowired
    ResearcherService researcherService;
    @Autowired
    RankingService rankingService;
    @Override

    public void run(String... args) {



        Users user1 = new Users();
        user1.setName("Finovatech Solutions");
        user1.setEmail("oly.meg@finovatech.com");
        user1.setField("Finance");
        user1.setHardSkills("Financial analysis and modeling (e.g., Excel, Power BI),Budget management and forecasting,Knowledge of accounting softwares,Regulatory compliance (e.g., GAAP, IFRS).");
        user1.setSoftSkills("Analytical thinking,Attention to detail,Risk management mindset");
        user1.setOtherTraits("Integrity, Curiosity, Resilience, Discipline");

        Users user2 = new Users();
        user2.setName("Workflow");
        user2.setEmail("maria.ili@workflow.com");
        user2.setField("Human Resources");
        user2.setHardSkills("Proficiency with HR tools (e.g., BambooHR, Workday), Recruitment and onboarding processes, Employee performance management systems,Knowledge of employment laws and regulations");
        user2.setSoftSkills("Empathy and active listening, Conflict resolution, Organizational skills,Leadership and team-building.");
        user2.setOtherTraits("Approachability, Fairness, Resourcefulness, Patience");



        Users user3 = new Users();
        user3.setName("SpotOn");
        user3.setEmail("alex.pap@spoton.com");
        user3.setField("Marketing and Communication");
        user3.setHardSkills("SEO/SEM and digital advertisingSocial media management tools (e.g., Hootsuite, Buffer).Graphic design and content creation (e.g., Canva, Adobe Suite),Public relations and media outreach");
        user3.setSoftSkills("Creativity and storytelling, Interpersonal communication, Time management for campaigns, Collaboration with cross-functional teams.");
        user3.setOtherTraits("Persuasiveness, Adaptability,Curiosity,Confidence");



        Users user4 = new Users();
        user4.setName("BuildUp");
        user4.setEmail("an.papandreou@buildup.com");
        user4.setField("Architecture");
        user4.setHardSkills("CAD software proficiency, Building codes and zoning regulations, Structural and material knowledge, Project management tools");
        user4.setSoftSkills(" Problem-solving and critical thinking, Visualization and spatial awareness, Communication with clients and stakeholders, Adaptability to design revisions");
        user4.setOtherTraits (" Visionary Thinking, Sustainability Focus, Attention to Detail, Open-mindedness ");



        usersService.saveUsers(user1);
        usersService.saveUsers(user2);
        usersService.saveUsers(user3);
        usersService.saveUsers(user4);


        ResearcherResult re_r1 = new ResearcherResult();
        re_r1.setResume("resume cv");
        ResearcherResult re_r2 = new ResearcherResult();
        re_r2.setResume("resume cv2");
        ResearcherResult re_r3 = new ResearcherResult();
        re_r3.setResume("resume cv3");
        ResearcherResult re_r4 = new ResearcherResult();
        re_r4.setResume("resume cv4");

        researcherService.saveResearcherResult(re_r1);
        researcherService.saveResearcherResult(re_r2);
        researcherService.saveResearcherResult(re_r3);
        researcherService.saveResearcherResult(re_r4);

        RankingResult ra_r1 = new RankingResult();
        ra_r1.setResume("resume cv1");
        ra_r1.setSummaryResume("summary resume1");
        RankingResult ra_r2 = new RankingResult();
        ra_r2.setResume("resume cv2");
        ra_r2.setSummaryResume("summary resume2");
        RankingResult ra_r3 = new RankingResult();
        ra_r3.setResume("resume cv3");
        ra_r3.setSummaryResume("summary resume3");
        RankingResult ra_r4 = new RankingResult();
        ra_r4.setResume("resume cv4");
        ra_r4.setSummaryResume("summary resume4");

        rankingService.saveRankingResult(ra_r1);
        rankingService.saveRankingResult(ra_r2);
        rankingService.saveRankingResult(ra_r3);
        rankingService.saveRankingResult(ra_r4);

       


    }
    

}