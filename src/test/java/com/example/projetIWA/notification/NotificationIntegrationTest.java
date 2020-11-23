package com.example.projetIWA.notification;

import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.github.database.rider.core.api.connection.ConnectionHolder;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith({DBUnitExtension.class, SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public class NotificationIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    public ConnectionHolder getConnectionHolder() {
        // Return a function that retrieves a connection
        // from our data source
        return () -> dataSource.getConnection();
    }

    @Test
    @DisplayName("GET /notifications/user/1 - Found")
    @DataSet("notificationIntegration.yml")
    void testGetUserByIdFound() throws Exception {
        // Execute the GET request
        mockMvc.perform(get("/notifications/user/{id}", "21590ad6-2296-447e-b4f3-cd6e7825ef8c"))

                //validate the response code ans content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].notification_id", is(1)))
                .andExpect(jsonPath("$[0].description", is("Contact cases")))
                .andExpect(jsonPath("$[0].viewed", is(true)))
                .andExpect(jsonPath("$[1].notification_id", is(2)))
                .andExpect(jsonPath("$[1].description", is("Contact cases")))
                .andExpect(jsonPath("$[1].viewed", is(true)));
    }

    @Test
    @DisplayName("GET /notifications/user/1/notViewed - Found")
    @DataSet("notificationIntegration.yml")
    public void testGetNumberNotificationsNotViewedByUserId() throws Exception {

        // Execute the GET request
        mockMvc.perform(get("/notifications/user/{id}/notViewed", "21590ad6-2296-447e-b4f3-cd6e7825ef8c"))

                //validate the response code ans content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //validate the returned fields
                .andExpect(jsonPath("$", is(1)));
    }

    /**
     * To send json on body request
     */
    @Autowired private ObjectMapper mapper;

    @Test
    @DisplayName("POST /notifications/user - Success")
    public void testCreateByUser() throws Exception {
        // setup the mocked service
        User user = new User();
        user.setUser_id("21590ad6-2296-447e-b4f3-cd6e7825ef8c");

        // Execute the POST request
        mockMvc.perform(post("/notifications/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))

                //validate the response code ans content type
                .andExpect(status().isCreated());
    }
}
