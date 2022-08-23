CREATE TABLE `RECIPE_INFO` (
    RECIPE_ID       BIGINT(20)      NOT NULL        COMMENT '레시피 코드',
    RECIPE_NM_KO    VARCHAR(50)     DEFAULT NULL    COMMENT '레시피 이름(한글)',
    SUMMARY         VARCHAR(100)    DEFAULT NULL    COMMENT '간략(요약) 소개',
    NATION_CD       VARCHAR(20)     DEFAULT NULL    COMMENT '유형코드',
    NATION_NM       VARCHAR(50)     DEFAULT NULL    COMMENT '유형분류',
    TYPE_CD         VARCHAR(20)     DEFAULT NULL    COMMENT '음식분류코드',
    TYPE_NM         VARCHAR(50)     DEFAULT NULL    COMMENT '음식분류',
    COOKING_TIME    VARCHAR(20)     DEFAULT NULL    COMMENT '조리시간',
    CALORIE         VARCHAR(20)     DEFAULT NULL    COMMENT '칼로리',
    QNT             VARCHAR(20)     DEFAULT NULL    COMMENT '분량',
    LEVEL_NM        VARCHAR(20)     DEFAULT NULL    COMMENT '난이도',
    IRDNT_CD        VARCHAR(20)     DEFAULT NULL    COMMENT '재료별 분류명',
    PC_NM           VARCHAR(50)     DEFAULT NULL    COMMENT '가격별 분류',
    LIKE_CNT        BIGINT(20)      DEFAULT NULL    COMMENT '좋아요 수',
    IMG_URL         VARCHAR(100)    DEFAULT NULL    COMMENT '음식 사진 url',

    PRIMARY KEY (RECIPE_ID)
) COMMENT '레시피 기본정보';

CREATE TABLE `RECIPE_CRSE` (
   RECIPE_ID           BIGINT(20)      NOT NULL        COMMENT '레시피 코드',
   COOKING_NO          BIGINT(20)      NOT NULL        COMMENT '요리설명순서',
   COOKING_DC          VARCHAR(1000)   DEFAULT NULL    COMMENT '요리설명',
   STEP_TIP            VARCHAR(1000)   DEFAULT NULL    COMMENT '과정팁',
   STRE_STEP_IMG_URL   VARCHAR(500)    DEFAULT NULL    COMMENT '과정 이미지 URL',

   PRIMARY KEY (RECIPE_ID, COOKING_NO),
   FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE_INFO (RECIPE_ID)
) COMMENT '레시피 과정정보';

CREATE TABLE `RECIPE_IRDNT` (
    RECIPE_ID       BIGINT(20)      NOT NULL        COMMENT '레시피 코드',
    IRDNT_SN        BIGINT(20)      NOT NULL        COMMENT '재료순번',
    IRDNT_NM        VARCHAR(100)    DEFAULT NULL    COMMENT '재료명',
    IRDNT_CPCTY     VARCHAR(100)    DEFAULT NULL    COMMENT '재료용량',
    IRDNT_TY_CD     VARCHAR(50)     DEFAULT NULL    COMMENT '재료타입 코드',
    IRDNT_TY_NM     VARCHAR(20)     DEFAULT NULL    COMMENT '재료타입명',

    PRIMARY KEY (RECIPE_ID, IRDNT_SN),
    FOREIGN KEY (RECIPE_ID) REFERENCES RECIPE_INFO (RECIPE_ID)
) COMMENT '레시피 재료정보';

create table RECIPE_INFO (
    recipe_id bigint not null comment '레시피 코드' primary key,
    recipe_nm_ko varchar(50) null comment '레시피 이름(한글)',
    summary varchar(100) null comment '간략(요약) 소개',
    nation_cd varchar(20) null comment '유형코드',
    nation_nm varchar(50) null comment '유형분류',
    type_cd varchar(20) null comment '음식분류코드',
    type_nm varchar(50) null comment '음식분류',
    cooking_time varchar(20) null comment '조리시간',
    calorie varchar(20) null comment '칼로리',
    qnt varchar(20) null comment '분량',
    level_nm varchar(20) null comment '난이도',
    irdnt_cd varchar(20) null comment '재료별 분류명',
    pc_nm varchar(50) null comment '가격별 분류',
    like_cnt bigint null comment '좋아요 수',
    img_url varchar(100) null comment '음식 사진 url'
) comment '레시피 기본정보';

create table RECIPE_CRSE ( recipe_id bigint not null comment '레시피 코드', cooking_no bigint not null comment '요리설명순서', cooking_dc varchar(1000) null comment '요리설명', step_tip varchar(1000) null comment '과정팁', stre_step_img_url varchar(500) null comment '과정 이미지 URL', id bigint not null, primary key (recipe_id, cooking_no), constraint FKm8jasmgjejh2ulht0e3q824x8 foreign key (recipe_id) references RECIPE_INFO (recipe_id) ) comment '레시피 과정정보';
create table RECIPE_IRDNT ( recipe_id bigint not null comment '레시피 코드', irdnt_sn bigint not null comment '재료순번', irdnt_nm varchar(100) null comment '재료명', irdnt_cpcty varchar(100) null comment '재료용량', irdnt_type_cd varchar(50) null comment '재료타입 코드', irdnt_type_nm varchar(20) null comment '재료타입명', id bigint not null, primary key (recipe_id, irdnt_sn), constraint FKf1utbwfxs6ldcoqc6in4swga7 foreign key (recipe_id) references RECIPE_INFO (recipe_id) ) comment '레시피 재료정보';


create table USER (
    EMAIL varchar(50) not null comment '사용자 아이디' primary key,
    NICKNAME varchar(50) not null comment '사용자 닉네임',
    PASSWORD varchar(100) null comment '비밀번호',
    IMG_URL varchar(100) null comment '프로필 사진 URL'
) comment '사용자 관리 테이블' collate = utf8mb4_unicode_ci;

create table CURRENT_RECIPE ( EMAIL varchar(50) not null comment '사용자 아이디', RECIPE_ID bigint not null comment '레시피 코드', VIEW_DATE datetime default current_timestamp() null comment '조회 시간 YYYY-MM-DD HH:mm:SS', primary key (EMAIL, RECIPE_ID), constraint CURRENT_RECIPE_ibfk_1 foreign key (EMAIL) references USER (EMAIL) on delete cascade, constraint CURRENT_RECIPE_ibfk_2 foreign key (RECIPE_ID) references RECIPE_INFO (recipe_id) ) comment '최근 조회한 레시피 관리 테이블' collate = utf8mb4_unicode_ci;
create index RECIPE_ID on CURRENT_RECIPE (RECIPE_ID);
create table RECIPE_BOOK_MARK ( EMAIL varchar(50) not null comment '사용자 아이디', RECIPE_ID bigint not null comment '레시피 코드', primary key (EMAIL, RECIPE_ID), constraint RECIPE_BOOK_MARK_ibfk_1 foreign key (EMAIL) references USER (EMAIL) on delete cascade, constraint RECIPE_BOOK_MARK_ibfk_2 foreign key (RECIPE_ID) references RECIPE_INFO (recipe_id) ) comment '레시피 북마크 관리 테이블' collate = utf8mb4_unicode_ci; create index RECIPE_ID on RECIPE_BOOK_MARK (RECIPE_ID);
create table RECIPE_LIKE ( EMAIL varchar(50) not null comment '사용자 아이디', RECIPE_ID bigint not null comment '레시피 코드', primary key (EMAIL, RECIPE_ID), constraint RECIPE_LIKE_ibfk_1 foreign key (EMAIL) references USER (EMAIL), constraint RECIPE_LIKE_ibfk_2 foreign key (RECIPE_ID) references RECIPE_INFO (recipe_id) ) comment '레시피 좋아요 관리 테이블' collate = utf8mb4_unicode_ci;
create index RECIPE_ID on RECIPE_LIKE (RECIPE_ID); create table REFRESH_TOKEN ( EMAIL varchar(50) not null comment '사용자 아이디' primary key, REFRESH_TOKEN varchar(200) null comment '사용자 아이디', constraint REFRESH_TOKEN_ibfk_1 foreign key (EMAIL) references USER (EMAIL) on delete cascade ) comment 'Refesh Token 관리 테이블' collate = utf8mb4_unicode_ci;
create table REFRIGERATOR ( EMAIL varchar(50) not null comment '사용자 아이디', IRDNT_NM varchar(100) not null comment '재료명', primary key (EMAIL, IRDNT_NM), constraint REFRIGERATOR_ibfk_1 foreign key (EMAIL) references USER (EMAIL) on delete cascade ) comment '사용자 별 냉장고 관리 테이블' collate = utf8mb4_unicode_ci;

