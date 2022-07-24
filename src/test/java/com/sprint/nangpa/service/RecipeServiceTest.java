package com.sprint.nangpa.service;


import com.sprint.nangpa.repository.RecipeIrdntClassRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecipeServiceTest {

    @Autowired
    RecipeIrdntClassRepository recipeIrdntClassRepository;
    @Autowired RecipeService recipeService;

    @Test
    public void 재료이름중복없이가져와() throws Exception {
        //given
        List<String> allNoOverlapIrdnt = recipeIrdntClassRepository.getAllNoOverlapIrdnt();

        //when

        //then

    }

}
