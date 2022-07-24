package com.sprint.nangpa.controller;


import com.sprint.nangpa.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/recipe/getIrdnt")
    @ResponseBody
    public List<String> getIrdnt(){
        return recipeService.getAllNoOverlapIrdnt();
    }
}
