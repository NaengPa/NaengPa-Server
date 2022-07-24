package com.sprint.nangpa.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/recipeData")
public class RecipeDataController {

    private final RecipeDataService recipeDataService;

    @PostMapping("/setData")
    public void saveRecipeData() throws Throwable {
        recipeDataService.saveRecipeInfoData();
        recipeDataService.saveRecipeCrseData();
        recipeDataService.saveRecipeIrdntData();

    }
}
