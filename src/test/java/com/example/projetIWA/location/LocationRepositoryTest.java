package com.example.projetIWA.location;

import com.example.projetIWA.models.Location;
import com.example.projetIWA.models.Notification;
import com.example.projetIWA.repositories.LocationRepository;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.Date;

@ExtendWith({DBUnitExtension.class,
        SpringExtension.class})
@SpringBootTest
@ActiveProfiles("test")
public class LocationRepositoryTest {

    @Autowired
    private DataSource dataSource;

    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }

    @Autowired
    private LocationRepository locationRepository;

    @Test
    void testSaveAndFlush() {
        // Setup
        Date date = new Date();
        Location location = new Location();
        location.setLatitude(15.151);
        location.setLongitude(4.48494);
        location.setLocation_date(date);

        // test
        Location locationCreate = locationRepository.saveAndFlush(location);

        Assertions.assertEquals(1, locationCreate.getLocation_id(),"Expected location id is 1");
        Assertions.assertEquals(15.151, locationCreate.getLatitude(),"Expected latitude is 15.151");
        Assertions.assertEquals(4.48494, locationCreate.getLongitude(),"Expected longitude is 4.48494");
        Assertions.assertEquals(date, locationCreate.getLocation_date(),"Expected date not good");
    }
}
