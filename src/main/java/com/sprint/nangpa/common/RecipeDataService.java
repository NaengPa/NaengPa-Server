package com.sprint.nangpa.common;

import com.sprint.nangpa.model.RecipeCrse;
import com.sprint.nangpa.model.RecipeInfo;
import com.sprint.nangpa.model.RecipeIrdnt;
import com.sprint.nangpa.repository.RecipeCrseRepository;
import com.sprint.nangpa.repository.RecipeIrdntRepository;
import com.sprint.nangpa.repository.RecipeInfoRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

/**
 * 레시피 데이터 관리
 *
 * 레시피 데이터 요청(Open API)
 * 응답 레시피 DB에 저장
 */
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RecipeDataService {

    // 공공데이터 요청 url
    final String API_URL = "http://211.237.50.150:7080/openapi";
    // 인증키
    final String API_KEY = "/9dbe4d02f76c2d99f066becd9d9a3183955c00888cfa7b8d71e70e51a72d0407";
    // 응답 타입
    final String TYPE    = "/json";

    // 서비스 요청 URL
    final String URL     = API_URL + API_KEY + TYPE;

    /**
     * 레시피 기본정보 데이터 관리 Repository
     */
    private final RecipeInfoRepository recipeInfoRepository;

    /**
     * 레시피 과정정보 데이터 관리 Repository
     */
    private final RecipeCrseRepository recipCrseRepository;

    /**
     * 레시피 과정정보 데이터 관리 Repository
     */
    private final RecipeIrdntRepository recipIrdntRepository;

    /**
     * 레시피 기본정보 데이터 DB 저장
     */
    public void saveRecipeInfoData(){
        // OpenAPI 서비스 URL
        final String seviceUrl = "Grid_20150827000000000226_1";

        // 데이터 요청 건 수 (endIdx - strIdx + 1)
        int strIdx = 1;
        int endIdx = 1000;

        int totCnt = Integer.MAX_VALUE;

        while (totCnt >= strIdx) {
            // OpenAPI URL 세팅
            StringBuilder sb = new StringBuilder(URL);
            sb.append("/").append(seviceUrl);
            sb.append("/").append(strIdx);
            sb.append("/").append(endIdx);

            try {
                // Http 연결 객체 생성
                HttpURLConnection con = (HttpURLConnection) new URL(sb.toString()).openConnection();
                con.setConnectTimeout(500); // 연결 대기시간 0.5초 설정
                con.connect();

                // 데이터 응답
                BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
                String line = reader.readLine();

                // 데이터 추출
                JSONObject jsonObject = new JSONObject(line).getJSONObject(seviceUrl);
                totCnt = jsonObject.getInt("totalCnt");
                JSONArray recipeData = jsonObject.getJSONArray("row");

                // 데이터 저장
                for (int i = 0; i < recipeData.length(); i++) {
                    JSONObject data = recipeData.getJSONObject(i);

                    // 저장 객체 생성
                    RecipeInfo recipeInfo = RecipeInfo.builder()
                            .recipeId(data.getLong("RECIPE_ID"))            // 레시피 코드
                            .recipeNmKo(data.getString("RECIPE_NM_KO"))     // 레시피 이름(한글)
                            .summary(data.getString("SUMRY"))               // 간략(요약) 소개
                            .nationCd(data.getString("NATION_CODE"))        // 유형코드
                            .nationNm(data.getString("NATION_NM"))          // 유형분류
                            .typeCd(data.getString("TY_CODE"))              // 음식분류코드
                            .typeNm(data.getString("TY_NM"))                // 음식분류
                            .cookingTime(data.getString("COOKING_TIME"))    // 조리시간
                            .calorie(data.getString("CALORIE"))             // 칼로리
                            .qnt(data.getString("QNT"))                     // 분량
                            .levelNm(data.getString("LEVEL_NM"))            // 난이도
                            .irdntCd(data.getString("IRDNT_CODE"))          // 재료별 분류명
                            .pcNm(data.getString("PC_NM"))                  // 가격별 분류
                            .likeCnt((long) (Math.random() * 1000))               // 좋아요 수
                            .build();

                    // 데이터 저장
                    recipeInfoRepository.save(recipeInfo);

                    // 한번에 최대 데이터 요청 건수 1000개
                    strIdx += 1000;
                    endIdx += 1000;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 레시피 과정정보 데이터 DB 저장
     */
    public void saveRecipeCrseData() throws Throwable{
        // OpenAPI 서비스 URL
        final String seviceUrl = "Grid_20150827000000000228_1";

        // 데이터 요청 건 수 (endIdx - strIdx + 1)
        int strIdx = 1;
        int endIdx = 1000;

        int totCnt = Integer.MAX_VALUE;


        while (totCnt >= strIdx) {
            // OpenAPI URL 세팅
            StringBuilder sb = new StringBuilder(URL);
            sb.append("/").append(seviceUrl);
            sb.append("/").append(strIdx);
            sb.append("/").append(endIdx);

            try {
                // Http 연결 객체 생성
                HttpURLConnection con = (HttpURLConnection) new URL(sb.toString()).openConnection();
                con.setConnectTimeout(500); // 연결 대기시간 0.5초 설정
                con.connect();

                // 데이터 응답
                BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
                String line = reader.readLine();

                // 데이터 추출
                JSONObject jsonObject = new JSONObject(line).getJSONObject(seviceUrl);
                totCnt = jsonObject.getInt("totalCnt");
                JSONArray recipeData = jsonObject.getJSONArray("row");

                long recipeId = 0;
                Optional<RecipeInfo> recipeInfo = null;

                // 데이터 저장
                for (int i = 0; i < recipeData.length(); i++) {
                    JSONObject data = recipeData.getJSONObject(i);

                    // 이전 데이터와 레시피 코드가 다른경우
                    if(recipeId != data.getLong("RECIPE_ID")){

                        // 레시피 정보 조회
                        recipeInfo = recipeInfoRepository.findById(data.getLong("RECIPE_ID"));
//                                .orElseThrow(() -> {
//                                    // 레시피 기본정보 미조회 예외 발생
//                                    throw new NotFoundRecipeInfoException();
//                                });

                        if(!recipeInfo.isPresent()){
                            continue;
                        }

                        recipeId = data.getLong("RECIPE_ID");
                    }

                    // 저장 객체 생성
                    RecipeCrse recipeCrse = RecipeCrse.builder()
                            .recipeInfo(recipeInfo.get())                                      // 레시피 정보(레시피 코드)
                            .cookingNo(data.getLong("COOKING_NO"))                  // 요리설명순서
                            .cookingDc(data.getString("COOKING_DC"))                // 요리설명
                            .stepTip(data.getString("STEP_TIP"))                    // 과정팁
                            .streStepImgUrl(data.getString("STRE_STEP_IMAGE_URL"))  // 과정 이미지 URL
                            .build();

                    System.out.println(recipeInfo+"/"+data.getLong("COOKING_NO")+" : "+recipeCrse);
                    // 데이터 저장
                    recipCrseRepository.save(recipeCrse);
                }

                // 한번에 최대 데이터 요청 건수 1000개
                strIdx += 1000;
                endIdx += 1000;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 레시피 과정정보 데이터 DB 저장
     */
    public void saveRecipeIrdntData() throws Throwable{
        // OpenAPI 서비스 URL
        final String seviceUrl = "Grid_20150827000000000227_1";

        // 데이터 요청 건 수 (endIdx - strIdx + 1)
        int strIdx = 1;
        int endIdx = 1000;

        int totCnt = Integer.MAX_VALUE;

        while (totCnt >= strIdx) {
            // OpenAPI URL 세팅
            StringBuilder sb = new StringBuilder(URL);
            sb.append("/").append(seviceUrl);
            sb.append("/").append(strIdx);
            sb.append("/").append(endIdx);

            try {
                // Http 연결 객체 생성
                HttpURLConnection con = (HttpURLConnection) new URL(sb.toString()).openConnection();
                con.setConnectTimeout(500); // 연결 대기시간 0.5초 설정
                con.connect();

                // 데이터 응답
                BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
                String line = reader.readLine();

                // 데이터 추출
                JSONObject jsonObject = new JSONObject(line).getJSONObject(seviceUrl);
                totCnt = jsonObject.getInt("totalCnt");
                JSONArray recipeData = jsonObject.getJSONArray("row");

                long recipeId = 0;
                Optional<RecipeInfo> recipeInfo = null;

                // 데이터 저장
                for (int i = 0; i < recipeData.length(); i++) {
                    JSONObject data = recipeData.getJSONObject(i);

                    // 이전 데이터와 레시피 코드가 다른경우
                    if(recipeId != data.getLong("RECIPE_ID")){

                        // 레시피 정보 조회
                        recipeInfo = recipeInfoRepository.findById(data.getLong("RECIPE_ID"));
//                                .orElseThrow(() -> {
//                                    // 레시피 기본정보 미조회 예외 발생
//                                    throw new NotFoundRecipeInfoException();
//                                });

                        if(!recipeInfo.isPresent()){
                            continue;
                        }

                        recipeId = data.getLong("RECIPE_ID");
                    }

                    // 저장 객체 생성
                    RecipeIrdnt recipeIrdnt = RecipeIrdnt.builder()
                            .recipeInfo(recipeInfo.get())                              // 레시피 정보(레시피 코드)
                            .irdntSn(data.getLong("IRDNT_SN"))              // 재료순번
                            .irdntNm(data.getString("IRDNT_NM"))            // 재료명
                            .irdntCpcty(data.getString("IRDNT_CPCTY"))      // 재료용량
                            .irdntTypeCd(data.getString("IRDNT_TY_CODE"))   // 재료타입 코드
                            .irdntTypeNm(data.getString("IRDNT_TY_NM"))     // 재료타입명
                            .build();

                    System.out.println(recipeInfo+"/"+data.getLong("IRDNT_SN")+" : "+recipeIrdnt);
                    // 데이터 저장
                    recipIrdntRepository.save(recipeIrdnt);
                }

                // 한번에 최대 데이터 요청 건수 1000개
                strIdx += 1000;
                endIdx += 1000;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
