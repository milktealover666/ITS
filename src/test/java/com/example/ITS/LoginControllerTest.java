package com.example.ITS;

import com.example.ITS.Controller.LoginController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;

    @Test
    public void testLoginPage() {
        String result = loginController.loginPage();
        assertEquals("login", result);
    }

    // @Test
    // public void testLogin() {
    //     User user = new User();
    //     MockHttpSession session = new MockHttpSession();
    //     Model model = new ExtendedModelMap();
    //     LoginService loginService = mock(LoginService.class);
    //     when(loginService.login(any(User.class))).thenReturn(user);
    //     String result = loginController.login(user, session, model);
    //     assertEquals("main", result);
    //     verify(loginService, times(1)).login(user);
    // }
}
