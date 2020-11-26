package com.example.projetIWA.location;

import com.example.projetIWA.auth.AuthService;
import com.example.projetIWA.controllers.LocationsViewController;
import com.example.projetIWA.services.LocationsService;
import com.example.projetIWA.services.NotificationsService;
import com.example.projetIWA.services.UsersServices;
import org.junit.jupiter.api.DisplayName;
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

@WebMvcTest(LocationsViewController.class)
@AutoConfigureMockMvc
@ComponentScan(basePackageClasses = { KeycloakSecurityComponents.class, KeycloakSpringBootConfigResolver.class })
public class LocationViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationsService locationsService;

    @MockBean
    private AuthService authService;

    @MockBean
    private NotificationsService notificationsService;

    @MockBean
    private UsersServices usersServices;

    @Test
    @DisplayName("GET /notificationView REDIRECT")
    void userAccount() throws Exception {
        mockMvc.perform(get("/notificationView"))
                .andExpect(status().is3xxRedirection());
    }
}
