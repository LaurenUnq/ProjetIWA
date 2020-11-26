package com.example.projetIWA.location;

import com.example.projetIWA.kafka.KafkaProducerService;
import com.example.projetIWA.kafka.models.UserLocalisation;
import com.example.projetIWA.models.Location;
import com.example.projetIWA.models.User;
import com.example.projetIWA.repositories.LocationRepository;
import com.example.projetIWA.repositories.UserRepository;
import com.example.projetIWA.services.LocationsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationServiceTest {

    /**
     * The service to test
     */
    @Autowired
    private LocationsService locationsService;

    @MockBean
    private KafkaProducerService kafkaProducerService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private LocationRepository locationRepository;

    @Test
    @DisplayName("Test create location")
    public void testCreateLocation() {
        //Setup our mock
        Location location = new Location();
        location.setLatitude(15.151);
        location.setLongitude(4.48494);
        location.setLocation_date(new Date());

        doNothing().when(kafkaProducerService).saveCreateUserLog(new UserLocalisation());

        // Excecute the service
        this.locationsService.create(location, "1");
    }

    @Test
    @DisplayName("Save in db location")
    public void testSaveInDbLocation() {
        //Setup our mock
        Location location = new Location();
        location.setLatitude(15.151);
        location.setLongitude(4.48494);
        location.setLocation_date(new Date());
        UserLocalisation userLocalisation = new UserLocalisation();
        userLocalisation.setLocation(location);
        userLocalisation.setUser_id("1");
        User user = new User();
        user.setUser_id("1");

        doReturn(Optional.of(user)).when(userRepository).findById("1");
        doReturn(Optional.of(user)).when(userRepository).saveAndFlush(user);
        doReturn(location).when(locationRepository).saveAndFlush(location);

        // Excecute the service
        this.locationsService.create(location, "1");
    }
}
