package com.sprint.nangpa.controller;

import com.sprint.nangpa.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * UserController 통합 테스트
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(new UserControllerTest())
                .build();
    }

    /**
     * 회원가입 테스트
     */
    @Test
    public void signInSuccess() {
//        mvc.perform(post("/user/signIn"))
//                .andExpect()
    }

    /**
     * 로컬 로그인 테스트
     */

}
