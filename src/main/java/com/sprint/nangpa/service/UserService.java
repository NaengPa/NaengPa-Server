package com.sprint.nangpa.service;

import com.sprint.nangpa.config.security.jwt.JwtTokenProvider;
import com.sprint.nangpa.dto.token.RefreshTokenDTO;
import com.sprint.nangpa.dto.user.SignInDto;
import com.sprint.nangpa.mapper.TokenMapper;
import com.sprint.nangpa.mapper.UserMapper;
import com.sprint.nangpa.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class UserService {



    private final UserMapper userMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final TokenService tokenService;

    private final TokenMapper tokenMapper;


    /**
     * 사용자 정보 저장
     *
     * @param  user    : 저장 정보
     * @return boolean : 저장 결과
     */
    public boolean saveUser(User user){
        int res = userMapper.insertUserInfo(user);

        return res == 1;
    }

    /**
     * 이메일 중복 확인
     *
     * @param  email   : 확인 이메일
     * @return boolean : 중복 결과 (true => 중복 됨)
     */
    public boolean duplCheckEmail(String email) {
        User user = userMapper.selectUserInfo(email);

        return user != null;
    }

    /**
     * 닉네임 중복 확인
     *
     * @param  nickname : 확인 닉네임
     * @return boolean  : 중복 결과 (true => 중복 됨)
     */
    public boolean duplCheckNickname(String nickname) {
        User user = userMapper.selectUserInfoByNickname(nickname);

        return user != null;
    }


    /**
     * 유저 정보 조회
     *
     * @param email : 유저 이메일
     * @return User : 유저 정보
     */
    public User getUserInfo(String email) {
        return userMapper.selectUserInfo(email);
    }

    /**
     * 로그인 유저 정보 조회
     *
     * @param email : 유저 이메일
     * @return User : 유저 정보
     */
    public User selectLoginUserInfo(String email) {
        return userMapper.selectLoginUserInfo(email);
    }

    /**
     * 회원 가입
     *
     * @param submittedUserInfo : 회원 가입
     * @return String           : 로그인 성공 여부
    */
    public String signUp(User submittedUserInfo){

        boolean duplCheckEmail = duplCheckEmail(submittedUserInfo.getEmail());
        boolean duplCheckNickname = duplCheckNickname(submittedUserInfo.getNickname());


        if (duplCheckEmail) {
            throw new DuplicateKeyException("중복된 이메일 입니다.");
        }

        if (duplCheckNickname) {
            throw new DuplicateKeyException("중복된 닉네임 입니다.");
        }

        User user = new User();

        user.setEmail(submittedUserInfo.getEmail());
        user.setNickname(submittedUserInfo.getNickname());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(submittedUserInfo.getPassword()));

        boolean isUserSaved = saveUser(user);

        try {
            if (!isUserSaved) {
                throw new AuthenticationException("회원가입에 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

        return "회원가입에 성공했습니다.";
    }

    /**
     * 로그인 함수
     *
     * @Param    signInDto : 아이디, 비밀번호
     * @return accessToken : 로그인시 발급해주는 엑세스 토큰
     */
    public HashMap<String, String> signIn(SignInDto signInDto) throws UsernameNotFoundException{
        User user = selectLoginUserInfo(signInDto.getEmail());

        if (user == null) {
            throw new UsernameNotFoundException("이메일에 등록된 유저가 없습니다. 이메일을 다시 한번 확인해주세요.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new UsernameNotFoundException("비밀번호가 일치 하지 않습니다.");
        }

        String refreshToken = this.tokenService.issueRefreshToken(signInDto.getEmail());

        String accessToken = this.jwtTokenProvider.makeJwtToken(signInDto.getEmail(), 30);

        HashMap<String, String> tokenMap = new HashMap<>();

        tokenMap.put("accessToken", accessToken);

        tokenMap.put("refreshToken", refreshToken);

        return tokenMap;

    }


    /**
     * 리프레쉬 토큰으로 액세스 토큰 갱신해주는 함수
     * @param refreshToken : 리프레쉬 토큰
     * @return accessToken : 새로 갱신된 엑세스 토큰
     */
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public HashMap<String, String> refreshAccessToken(String refreshToken) {
        //리프레쉬 토큰을 받아서 토큰이 만료되었는지, 토큰이 유저 디비에 저장된 토큰과 동일한지 검사 한 후
        Claims claims;
        HashMap<String, String> tokenMap = new HashMap<>();
        try {
            claims  = (Claims) jwtTokenProvider.validateToken(refreshToken);
        } catch (ExpiredJwtException expiredJwtException) {
            throw expiredJwtException;
        }

        String email = (String)claims.get("email");

        RefreshTokenDTO refreshTokenDTO = tokenMapper.selectRefreshToken(email);

        if (refreshTokenDTO.getRefreshToken() == refreshToken) {
            //토큰이 유효하면 새로운 엑세스 토큰이랑 리프레쉬 토큰을 발급해준다.
            String issuedRefreshToken = tokenService.issueRefreshToken(email);
            tokenMap.put("refreshToken", issuedRefreshToken);
            String accessToken = this.jwtTokenProvider.makeJwtToken(email, 30);
            tokenMap.put("accessToken", accessToken);
        }else{
            throw new UsernameNotFoundException("올바른 리프레쉬 토큰이 아닙니다.");
        }

        return tokenMap;

    }
}