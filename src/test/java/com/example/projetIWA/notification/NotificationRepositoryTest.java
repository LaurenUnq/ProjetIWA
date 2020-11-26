package com.example.projetIWA.notification;

import com.example.projetIWA.models.Notification;
import com.example.projetIWA.repositories.NotificationRepository;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith({DBUnitExtension.class,
        SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
public class NotificationRepositoryTest {

    @Autowired
    private DataSource dataSource;

    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }
    @Autowired
    private NotificationRepository repository;

    @Test
    @DataSet("notifications.yml")
    void testFindById() {
        long id = 1;
        Optional<Notification> notification = repository.findById(id);
        Assertions.assertEquals(true, notification.isPresent(), "Expected, notification should be found");
        Assertions.assertEquals(1, notification.get().getNotification_id(),"Expected notification id is 1");
        Assertions.assertEquals("Contact cases", notification.get().getDescription(),"Expected description is \"Contact cases\"");
        Assertions.assertEquals(true, notification.get().getViewed(),"Expected view is true");
    }

    @Test
    void testSaveAndFlush() {
        // Setup
        Date date = new Date();
        Notification notification = new Notification();
        notification.setDescription("test cas contact");
        notification.setNotification_date(date);
        notification.setViewed(false);

        // test
        Notification notificationCreate = repository.saveAndFlush(notification);

        Assertions.assertEquals(1, notificationCreate.getNotification_id(),"Expected notification id is 1");
        Assertions.assertEquals("test cas contact", notificationCreate.getDescription(),"Expected description is \"test cas contact\"");
        Assertions.assertEquals(false, notificationCreate.getViewed(),"Expected view is false");
        Assertions.assertEquals(date, notification.getNotification_date(),"Expected date not good");
    }
}
