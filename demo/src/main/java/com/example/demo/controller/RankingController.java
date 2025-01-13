package com.example.demo.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.database.ranking.RankingRepository;
import com.example.demo.database.ranking.RankingResult;

/**
 * @author Maria Spachou
 */
@Controller
@RequestMapping("/hireandgo/home")
public class RankingController {

    @Autowired
    private RankingRepository rankingRepository;

    // display of ranking page
    @GetMapping("/ranking/{id}")
    public String rankingControl(Model model, @PathVariable("id") String id) {
        List<RankingResult> arr = rankingRepository.findBySessionId(id);
        model.addAttribute("items", new JSONArray(arr));
        return "ranking";
    }

}
