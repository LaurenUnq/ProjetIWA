package com.example.projetIWA.notification;

import com.example.projetIWA.controllers.NotificationsController;
import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.services.NotificationsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(NotificationsController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationsService notificationsService;

    @Test
    @DisplayName("GET /notifications/user/1 - Found")
    public void testGetAllNotificationsById() throws Exception {
        // setup the mocked service
        Date date = new Date();
        Notification n1 = new Notification();
        Notification n2 = new Notification();
        n1.setNotification_id(1);
        n1.setDescription("notif 1");
        n1.setViewed(false);
        n1.setNotification_date(date);
        n2.setNotification_id(2);
        n2.setDescription("notif 2");
        n2.setViewed(false);
        n2.setNotification_date(date);
        List<Notification> notifications = new ArrayList<Notification>();
        notifications.add(n1);
        notifications.add(n2);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String dateString = format.format(new Date(date.getTime() -1000*60*60)); //heure francaise


        doReturn(notifications).when(notificationsService).getAllNotificationByUserId("1");

        // Execute the GET request
        mockMvc.perform(get("/notifications/user/{id}", 1))

                //validate the response code ans content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].notification_id", is(1)))
                .andExpect(jsonPath("$[0].description", is("notif 1")))
                .andExpect(jsonPath("$[0].viewed", is(false)))
                .andExpect(jsonPath("$[0].notification_date", is(dateString + "+00:00")))
                .andExpect(jsonPath("$[1].notification_id", is(2)))
                .andExpect(jsonPath("$[1].description", is("notif 2")))
                .andExpect(jsonPath("$[1].viewed", is(false)))
                .andExpect(jsonPath("$[1].notification_date", is(dateString + "+00:00")));
    }

    @Test
    @DisplayName("GET /notifications/user/1/notViewed - Found")
    public void testGetNumberNotificationsNotViewedByUserId() throws Exception {
        // setup the mocked service
        Date date = new Date();
        Notification n1 = new Notification();
        Notification n2 = new Notification();
        n1.setNotification_id(1);
        n1.setDescription("notif 1");
        n1.setViewed(false);
        n1.setNotification_date(date);
        n2.setNotification_id(2);
        n2.setDescription("notif 2");
        n2.setViewed(false);
        n2.setNotification_date(date);
        List<Notification> notifications = new ArrayList<Notification>();
        notifications.add(n1);
        notifications.add(n2);


        doReturn(new Long(notifications.size())).when(notificationsService).getNumberNotificationNotViewedByUserId("1");

        // Execute the GET request
        mockMvc.perform(get("/notifications/user/{id}/notViewed", 1))

                //validate the response code ans content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //validate the returned fields
                .andExpect(jsonPath("$", is(2)));
    }

    @Autowired private ObjectMapper mapper;

    @Test
    @DisplayName("POST /notifications/user - Success")
    public void testCreateByUser() throws Exception {
        // setup the mocked service
        User user = new User();
        doNothing().when(notificationsService).createNotificationsFromPositiveUser(user);

        // Execute the POST request
        mockMvc.perform(post("/notifications/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(user)))

                //validate the response code ans content type
                .andExpect(status().isCreated());
    }
}
