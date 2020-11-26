package com.example.projetIWA.User;
import com.example.projetIWA.auth.AuthService;
import com.example.projetIWA.controllers.NotificationsController;
import com.example.projetIWA.controllers.UserAccountController;
import com.example.projetIWA.services.NotificationsService;
import com.example.projetIWA.services.UsersServices;
import org.junit.jupiter.api.Test;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserAccountController.class)
@AutoConfigureMockMvc
@ComponentScan(basePackageClasses = { KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class })
public class UserAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersServices usersServices;

    @MockBean
    private AuthService authService;

    @MockBean
    private NotificationsService notificationsService;

    @Test
    void userAccount() throws Exception {
        mockMvc.perform(get("/userAccount"))
                .andExpect(status().is3xxRedirection());
    }
}
