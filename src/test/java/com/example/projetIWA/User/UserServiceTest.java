package com.example.projetIWA.User;

import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.NotificationRepository;
import com.example.projetIWA.repositories.UserRepository;
import com.example.projetIWA.services.NotificationsService;
import com.example.projetIWA.services.UsersServices;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    /**
     * The service to test
     */
    @Autowired
    private UsersServices usersServices;
    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Test userExist Success")
    public void testUserExist() {
        //Setup our mock
        User user = new User();
        user.setUser_id("1");
        user.setUsername("JeanMichel");
        user.setEmail("jeanMichel@gmail.com");
        user.setLast_name("Michel");
        user.setFirst_name("Michel");

        User user2 = new User();
        user2.setUser_id("2");
        user2.setUsername("JeanMichel2");
        user2.setEmail("jeanMichel2@gmail.com");
        user2.setLast_name("Michel2");
        user2.setFirst_name("Michel2");

        doReturn(Optional.of(user)).when(userRepository).findById("1");

        // Excecute the servicer
        Boolean result = this.usersServices.userExist("1");

        Assertions.assertEquals(true, result, "User should exist");

    }

    @Test
    @DisplayName("Test user FindById Success")
    public void testFindById() {
        //Setup our mock
        User user = new User();
        user.setUser_id("1");
        user.setUsername("JeanMichel");
        user.setEmail("jeanMichel@gmail.com");
        user.setLast_name("Michel");
        user.setFirst_name("Michel");

        User user2 = new User();
        user2.setUser_id("2");
        user2.setUsername("JeanMichel2");
        user2.setEmail("jeanMichel2@gmail.com");
        user2.setLast_name("Michel2");
        user2.setFirst_name("Michel2");

        doReturn(Optional.of(user)).when(userRepository).findById("1");

        // Excecute the servicer
        User result = this.usersServices.findById("1");

        Assertions.assertEquals(user, result, "User should be user 1");

    }

    @Test
    @DisplayName("Test user FindAll Success")
    public void testFindAll() {
        //Setup our mock
        User user = new User();
        user.setUser_id("1");
        user.setUsername("JeanMichel");
        user.setEmail("jeanMichel@gmail.com");
        user.setLast_name("Michel");
        user.setFirst_name("Michel");

        User user2 = new User();
        user2.setUser_id("2");
        user2.setUsername("JeanMichel2");
        user2.setEmail("jeanMichel2@gmail.com");
        user2.setLast_name("Michel2");
        user2.setFirst_name("Michel2");

        List<User> listUser = new ArrayList<User>();
        listUser.add(user);
        listUser.add(user2);

        doReturn(listUser).when(userRepository).findAll();

        // Excecute the servicer
        List<User> result = this.usersServices.findAll();

        Assertions.assertEquals(listUser, result, "Test should return the list of users");

    }

    @Test
    @DisplayName("Test user Save Success")
    public void testSave() {
        //Setup our mock
        User user = new User();
        user.setUser_id("1");
        user.setUsername("JeanMichel");
        user.setEmail("jeanMichel@gmail.com");
        user.setLast_name("Michel");
        user.setFirst_name("Michel");

        doReturn(user).when(userRepository).save(user);

        // Excecute the servicer
        User result = this.usersServices.save(user);

        Assertions.assertEquals(user, result, "save should save the user");

    }
}
