package com.sprint.nangpa.service;

import com.sprint.nangpa.repository.RecipeIrdntClassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeIrdntClassRepository recipeIrdntClassRepository;

    @Transactional
    public List<String> getAllNoOverlapIrdnt(){
        return this.recipeIrdntClassRepository.getAllNoOverlapIrdnt();
    }


}
