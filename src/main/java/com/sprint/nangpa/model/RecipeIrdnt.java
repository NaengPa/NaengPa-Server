package com.sprint.nangpa.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 레시피 재료정보
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "RECIPE_IRDNT")
@ToString
public class RecipeIrdnt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 레시피 코드
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipeId")
    private RecipeInfo recipeInfo;

    /**
     * 재료순번
     */
    private Long irdntSn;

    /**
     * 재료명
     */
    @Column(length = 100)
    private String irdntNm;

    /**
     * 재료용량
     */
    @Column(length = 100)
    private String irdntCpcty;

    /**
     * 재료타입 코드
     */
    @Column(length = 50)
    private String irdntTypeCd;

    /**
     * 재료타입명
     */
    @Column(length = 20)
    private String irdntTypeNm;

    @Builder
    public RecipeIrdnt(RecipeInfo recipeInfo, Long irdntSn, String irdntNm, String irdntCpcty, String irdntTypeCd, String irdntTypeNm) {
        this.recipeInfo = recipeInfo;
        this.irdntSn = irdntSn;
        this.irdntNm = irdntNm;
        this.irdntCpcty = irdntCpcty;
        this.irdntTypeCd = irdntTypeCd;
        this.irdntTypeNm = irdntTypeNm;
    }
}
