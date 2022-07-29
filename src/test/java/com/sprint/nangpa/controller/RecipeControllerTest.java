package com.sprint.nangpa.controller;

import com.sprint.nangpa.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 통합 테스트
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    private MockMvc mvc;

    @Autowired
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(new RecipeController(recipeService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    /**
     * 재료정보 조회(중복제거) 테스트
     */
    @Test
    public void given_whenFindDistinctAllIrdnt_thenGetDistinctAllIrdntSuccess() throws Exception {
        mvc.perform(get("/recipe/getIrdnt"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(217)))
                .andDo(print());
    }

    /**
     * 재료정보가 포함된 레시피 기본정보 조회 테스트
     */
    @Test
    public void givenIrdntNmArray_whenFindRecipeInfoContainIrdntNms_thenGetRecipeInfoListSuccess() throws Exception {
        String jsonData = "\n" +
                "{\n" +
                "    \"irdntNms\":[\n" +
                "        \"밥\",\n" +
                "        \"계란\",\n" +
                "        \"간장\",\n" +
                "        \"참기름\"\n" +
                "    ]\n" +
                "}";

        mvc.perform(post("/recipe/getRecipeList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(32)))
                .andDo(print());
    }

    /**
     * 레시피 상세정보 조회 테스트
     */
    @Test
    public void givenPathVariableRecipeId_whenFindRecipeDetailInfoByRecipeId_thenGetRecipeDetailInfoSuccess() throws Exception {
        mvc.perform(get("/recipe/getRecipeDetail/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipeInfo.recipeId", is(1)))
                .andExpect(jsonPath("$.recipeCrses", hasSize(5)))
                .andExpect(jsonPath("$.recipeIrdnts", hasSize(17)))
                .andDo(print());
    }

    /**
     * 최근 조회한 레시피 기본정보 조회
     */
    @Test
    public void givenCurrentViewRecipeIdArray_whenFindCurrentViewRecipeInfo_thenGetCurrentViewRecipeInfoSuccess() throws Exception {
        String jsonData = "\n" +
                "{\n" +
                "    \"recipeIds\":[\n" +
                "        1,\n" +
                "        11,\n" +
                "        49\n" +
                "    ]\n" +
                "}";

        mvc.perform(post("/recipe/getCurRecipeList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].recipeId", is(1)))
                .andExpect(jsonPath("$.[1].recipeId", is(11)))
                .andExpect(jsonPath("$.[2].recipeId", is(49)))
                .andDo(print());
    }
}
