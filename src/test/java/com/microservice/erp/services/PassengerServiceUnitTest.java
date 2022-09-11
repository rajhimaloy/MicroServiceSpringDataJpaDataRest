package com.microservice.erp.services;

import com.microservice.erp.domain.entities.Passenger;
import com.microservice.erp.domain.models.Gender;
import com.microservice.erp.domain.repositories.PassengerRepository;
import com.microservice.erp.services.iServices.PassengerService;
import com.microservice.erp.services.impl.PassengerServiceImpl;
import com.microservice.erp.webapp.config.TestJPAH2Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {TestJPAH2Config.class})
public class PassengerServiceUnitTest {

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    PassengerRepository repository;

    @InjectMocks
    PassengerService service = new PassengerServiceImpl(repository);

    @Test
    public void happyPathTest(){
        //Defining Mock Object:
        Passenger aPassenger = new Passenger("Rajib", Gender.MALE, 36);
        when(repository.save(any(Passenger.class))).thenReturn(aPassenger);

        //Call controller to make the save:
        Passenger nPassenger = service.add(aPassenger);

        //Verify:
        assertNotNull(nPassenger);
        assertNotNull(nPassenger.getId());
        assertEquals("Rajib", nPassenger.getName());
        //System.out.println(nPassenger.marshallingToMap(true));
    }
}
