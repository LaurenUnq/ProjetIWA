package com.example.projetIWA.notification;

import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.NotificationRepository;
import com.example.projetIWA.repositories.UserRepository;
import com.example.projetIWA.services.NotificationsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificationServiceTest {

    /**
     * The service to test
     */
    @Autowired
    private NotificationsService notificationsService;

    @MockBean
    private NotificationRepository notificationRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test getAllNotificationByUserId Success")
    public void testGetAllNotificationByUserId() {
        //Setup our mock
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
        User user = new User();
        user.setUser_id("1");
        user.setNotifications(Arrays.asList(n1,n2));

        doReturn(user).when(userRepository).getOne("1");
        doReturn(user).when(userRepository).saveAndFlush(user);

        // Excecute the service
        List<Notification> notificationList = this.notificationsService.getAllNotificationByUserId("1");

        Assertions.assertEquals(2, notificationList.size(), "getAllNotificationByUserId should return 2 notifications");

    }
}
