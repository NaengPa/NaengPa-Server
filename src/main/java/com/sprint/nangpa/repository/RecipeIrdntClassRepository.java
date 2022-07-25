package com.sprint.nangpa.repository;

import com.sprint.nangpa.model.RecipeIrdnt;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RecipeIrdntClassRepository {

    private final EntityManager em;

    public RecipeIrdntClassRepository(EntityManager em) {
        this.em = em;
    }

    public void save(RecipeIrdnt irdnt){
        em.persist(irdnt);
    }

    public RecipeIrdnt findOne(Long id){
        return em.find(RecipeIrdnt.class, id);
    }

    /**디비에 있는 모든 재료를 중복없이 가져온다*/
    public List<String> getAllNoOverlapIrdnt(){
        List<RecipeIrdnt> irdntList = em.createQuery("select r from RecipeIrdnt r", RecipeIrdnt.class)
                .getResultList();

        Set<String> nameList = new HashSet<>();

        for(RecipeIrdnt irdnt : irdntList ){
            nameList.add(irdnt.getIrdntNm());
        }

        return new ArrayList<String>(nameList);
    }

}
