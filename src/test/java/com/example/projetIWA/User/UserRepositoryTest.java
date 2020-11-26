package com.example.projetIWA.User;

import com.example.projetIWA.models.Notification;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.NotificationRepository;
import com.example.projetIWA.repositories.UserRepository;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith({DBUnitExtension.class,
        SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private DataSource dataSource;

    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }
    @Autowired
    private UserRepository repository;

    @Test
    @DataSet("users.yml")
    void testFindById() {
        String id = "e9f7b368-f3ff-46d9-9d46-b60fabafedb8";
        Optional<User> user = repository.findById(id);
        Assertions.assertEquals(true, user.isPresent(), "Expected, user should be found");
        Assertions.assertEquals("e9f7b368-f3ff-46d9-9d46-b60fabafedb8", user.get().getUser_id(),"Expected user id is e9f7b368-f3ff-46d9-9d46-b60fabafedb8");
        Assertions.assertEquals("lauren.unquera", user.get().getUsername(),"Expected username is \"lauren.unquera\"");
        Assertions.assertEquals("Lauren", user.get().getFirst_name(),"Expected firstname is Lauren");
        Assertions.assertEquals("Unquera", user.get().getLast_name(),"Expected lastname is Unquera");
        Assertions.assertEquals("lu@gmail.com", user.get().getEmail(),"Expected email is lu@gmail.com");
    }

    @Test
    @DataSet("users.yml")
    void testFindAll() {
        List<User> users2 = repository.findAll();

        Assertions.assertEquals(3, users2.size(), "Expected, user list size should be 2");

        Assertions.assertEquals("21590ad6-2296-447e-b4f3-cd6e7825ef8c", users2.get(0).getUser_id(), "Expected, user id should be 21590ad6-2296-447e-b4f3-cd6e7825ef8c");
        Assertions.assertEquals("hugo.brando", users2.get(0).getUsername(),"Expected username user 1 is \"hugo.brando\"");
        Assertions.assertEquals("Hugo", users2.get(0).getFirst_name(),"Expected firstname user 1 is Hugo");
        Assertions.assertEquals("Brando", users2.get(0).getLast_name(),"Expected lastname user 1 is Brando");
        Assertions.assertEquals("hb@gmail.com", users2.get(0).getEmail(),"Expected email user 1 is hb@gmail.com");

        Assertions.assertEquals("e9f7b368-f3ff-46d9-9d46-b60fabafedb8", users2.get(1).getUser_id(), "Expected, user id should be e9f7b368-f3ff-46d9-9d46-b60fabafedb8");
        Assertions.assertEquals("lauren.unquera", users2.get(1).getUsername(),"Expected username user 2 is \"lauren.unquera\"");
        Assertions.assertEquals("Lauren", users2.get(1).getFirst_name(),"Expected firstname user 2 is Lauren");
        Assertions.assertEquals("Unquera", users2.get(1).getLast_name(),"Expected lastname user 2 is Unquera");
        Assertions.assertEquals("lu@gmail.com", users2.get(1).getEmail(),"Expected email user 2 is lu@gmail.com");
    }

    @Test
    @DataSet("users.yml")
    void testSave() {
        User toSave = new User();
        toSave.setUser_id("e9f7b368-f3ff-46d9-9d46-b60fabafedb8");
        toSave.setUsername("lauren.unquera");
        toSave.setFirst_name("Lauren");
        toSave.setLast_name("Unquera");
        toSave.setEmail("lu@gmail.com");

        repository.save(toSave);

        Optional<User> user = repository.findById("e9f7b368-f3ff-46d9-9d46-b60fabafedb8");
        Assertions.assertEquals(true, user.isPresent(), "Expected, user should be found");
        Assertions.assertEquals("e9f7b368-f3ff-46d9-9d46-b60fabafedb8", user.get().getUser_id(),"Expected user id is e9f7b368-f3ff-46d9-9d46-b60fabafedb8");
        Assertions.assertEquals("lauren.unquera", user.get().getUsername(),"Expected username is \"lauren.unquera\"");
        Assertions.assertEquals("Lauren", user.get().getFirst_name(),"Expected firstname is Lauren");
        Assertions.assertEquals("Unquera", user.get().getLast_name(),"Expected lastname is Unquera");
        Assertions.assertEquals("lu@gmail.com", user.get().getEmail(),"Expected email is lu@gmail.com");
    }

    @Test
    @DataSet("users.yml")
    void testFindAllContactCase() {
        List<User> users = repository.findAllContactCase(
                0.5237048,
                0.5236048,
                0.584748,
                0.584148,
                new Date(2020,11,14,17,07,20),
                new Date(2020,11,14,16,37,20),
                "21590ad6-2296-d-b4f3-cd6e7825ef8c");

        Assertions.assertEquals(0, users.size(), "Expected, one user should be found");
    }

}