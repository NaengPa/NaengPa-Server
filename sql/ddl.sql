CREATE TABLE `RECIPE_INFO` (
        RECIPE_ID       INT             COMMENT '레시피 코드',
        RECIPE_NM_KO    VARCHAR(50)     COMMENT '레시피 이름(한글)',
        SUMMARY         VARCHAR(100)    COMMENT '간략(요약) 소개',
        NATION_CD       VARCHAR(20)     COMMENT '유형코드',
        NATION_NM       VARCHAR(50)     COMMENT '유형분류',
        TYPE_CD         VARCHAR(20)     COMMENT '음식분류코드',
        TYPE_NM         VARCHAR(50)     COMMENT '음식분류',
        COOKING_TIME    VARCHAR(20)     COMMENT '조리시간',
        CALORIE         VARCHAR(20)     COMMENT '칼로리',
        QNT             VARCHAR(20)     COMMENT '분량',
        LEVEL_NM        VARCHAR(20)     COMMENT '난이도',
        IRDNT_CODE      VARCHAR(20)     COMMENT '재료별 분류명',
        PC_NM           VARCHAR(50)     COMMENT '가격별 분류',

        PRIMARY KEY (RECIPE_ID)
) COMMENT '레시피 기본정보';

CREATE TABLE `RECIPE_CRSE` (
        RECIPE_ID           INT             COMMENT '레시피 코드',
        COOKING_NO          INT             COMMENT '요리설명순서',
        COOKING_DC          VARCHAR(1000)   COMMENT '요리설명',
        STEP_TIP            VARCHAR(1000)   COMMENT '과정팁',
        STRE_STEP_IMAGE_URL VARCHAR(500)    COMMENT '과정 이미지 URL',

        PRIMARY KEY (RECIPE_ID, COOKING_NO),
        FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE_INFO (RECIPE_ID)
) COMMENT '레시피 과정정보';

CREATE TABLE `RECIPE_IRDNT` (
        RECIPE_ID       INT             COMMENT '레시피 코드',
        IRDNT_SN        INT             COMMENT 'IRDNT_SN',
        IRDNT_NM        VARCHAR(100)    COMMENT '재료명',
        IRDNT_CPCTY     VARCHAR(100)    COMMENT '재료용량',
        IRDNT_TY_CODE   VARCHAR(50)     COMMENT '재료타입 코드',
        IRDNT_TY_NM     VARCHAR(20)     COMMENT '재료타입명',

        PRIMARY KEY (RECIPE_ID, IRDNT_SN),
        FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE_INFO (RECIPE_ID)
) COMMENT '레시피 재료정보';