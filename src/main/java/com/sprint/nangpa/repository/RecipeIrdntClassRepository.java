package com.sprint.nangpa.repository;

import com.sprint.nangpa.model.RecipeIrdnt;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
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

        List<String> nameList = new ArrayList<>();

        for(RecipeIrdnt irdnt : irdntList ){
            nameList.add(irdnt.getIrdntNm());
        }

        //nameList의 중복 제거하고 새로운 리스트에 저장
        List<String> NoOverLapNameList = nameList.stream().distinct().collect(Collectors.toList());

        return NoOverLapNameList;
    }

}
