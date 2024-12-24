package com.example.demo.database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.database.reasearcher.ResearcherResult;
import com.example.demo.database.reasearcher.ResearcherService;
import com.example.demo.database.user.Users;
import com.example.demo.database.user.UsersService;

@Component
public class DatabseComponent implements CommandLineRunner {
    @Autowired
    UsersService usersService;
    @Autowired
    ResearcherService researcerService;
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


        ResearcherResult rr1 = new ResearcherResult();
        rr1.setResume("resume cv");
        ResearcherResult rr2 = new ResearcherResult();
        rr2.setResume("resume cv2");
        ResearcherResult rr3 = new ResearcherResult();
        rr3.setResume("resume cv3");
        ResearcherResult rr4 = new ResearcherResult();
        rr4.setResume("resume cv4");

        researcerService.saveResearcherResult(rr1);
        researcerService.saveResearcherResult(rr2);
        researcerService.saveResearcherResult(rr3);
        researcerService.saveResearcherResult(rr4);

    }
    

}