package com.sprint.nangpa.config.security.oauth;

import com.sprint.nangpa.config.security.jwt.JwtTokenProvider;
import com.sprint.nangpa.dto.user.UserInfoDTO;
import com.sprint.nangpa.mapper.UserMapper;
import com.sprint.nangpa.model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoService {

    private final UserMapper userMapper;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    final String clientId;

    final String redirect_uri;


    public KakaoService(UserMapper userMapper, @Value("${kakao_rest_api_key}") String clientId, @Value("${redirect_uri}") String redirect_uri,  JwtTokenProvider  jwtTokenProvider) {
        this.userMapper = userMapper;
        this.clientId = clientId;
        this.redirect_uri = redirect_uri;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public HashMap<String, String> getKakaoToken(String code) throws IOException {
        // 인가코드로 토큰받기
        String host = "https://kauth.kakao.com/oauth/token";
        URL url = new URL(host);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true); // 데이터 기록 알려주기

        HashMap<String, String> token = new HashMap<>();

        String sb = "grant_type=authorization_code" +
                "&client_id=" + clientId +
                "&redirect_uri=" + redirect_uri +
                "&code=" + code;

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()))) {
            bw.write(sb);
            bw.flush();

            try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                StringBuilder result = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                System.out.println("result = " + result);

                // json parsing
                JSONParser parser = new JSONParser();
                JSONObject elem = (JSONObject) parser.parse(result.toString());

                String access_token = elem.get("access_token").toString();
                String refresh_token = elem.get("refresh_token").toString();
                System.out.println("refresh_token = " + refresh_token);
                System.out.println("access_token = " + access_token);
                token.put("access_token", access_token);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }


    public Map<String, String> getKaKaoUserInfo(String access_token) {
        String host = "https://kapi.kakao.com/v2/user/me";
        Map<String, String> result = new HashMap<>(); //key, value json 형식으로 데이터 내보내기 위해 hashMap 사용

        try {
            URL url = new URL(host);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
            urlConnection.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))){
                String line;
                StringBuilder res = new StringBuilder();
                while((line=br.readLine())!=null)
                {
                    res.append(line);
                }

                System.out.println("res = " + res);

                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(res.toString());

                JSONObject properties = (JSONObject) obj.get("properties");

                String id = obj.get("id").toString();
                String nickname = properties.get("nickname").toString();
                String profile_image = properties.get("profile_image").toString();

                result.put("id", id);
                result.put("nickname", nickname);
                result.put("profile_image", profile_image);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String getAgreementInfo(String access_token) throws IOException {
        StringBuilder result = new StringBuilder();
        String host = "https://kapi.kakao.com/v2/user/scopes";

        URL url = new URL(host);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Authorization", "Bearer "+access_token);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            String line;
            while((line=br.readLine())!=null)
            {
                result.append(line);
            }

            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode = " + responseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }


<<<<<<< HEAD
    public String KakaoLogin(String code) throws IOException{
        HashMap<String, String> tokenData = this.getKakaoToken(code); // 인가 코드로 카카오 서버에 카카오 엑세스 토큰 요청
        Map<String, String> userInfo = this.getKaKaoUserInfo(tokenData.get("access_token"));  //카카오 서버에 카카오 엑세스 토큰으로 유저정보 요청
        if (IsUserEmpty(userInfo.get("id"))) { // 카카오 계정은 이매일이 카카오에서 주는 아이디값
=======
    public Map<String, String> KakaoLogin(String code) throws IOException{
        HashMap<String, String> tokenData = this.getKakaoToken(code);
        Map<String, String> userInfo = this.getKaKaoUserInfo(tokenData.get("access_token"));
        if (IsUserEmpty(userInfo.get("email"))) {
>>>>>>> 79f5af84cd26da421ba0db854f88b0f69a2f7993
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setEmail(userInfo.get("id"));
            userInfoDTO.setNickname(userInfo.get("nickname"));
            userInfoDTO.setImgUrl(userInfo.get("profile_image"));
            saveUser(userInfoDTO);
        }
        return this.jwtTokenProvider.makeJwtToken(userInfo.get("email"));
    }


    public void saveUser(UserInfoDTO userInfo) {
        User user = new User(userInfo);

        userMapper.insertUserInfo(user);
    }


    public boolean IsUserEmpty(String email) {
        User user = userMapper.selectUserInfo(email);
<<<<<<< HEAD
        if (user == null) {
            return true;
        }
        return false;
    }
=======
>>>>>>> 79f5af84cd26da421ba0db854f88b0f69a2f7993

        return user == null;
    }




}
