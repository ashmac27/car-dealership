package com.sg.cardealership.service;

import com.sg.cardealership.TestApplicationConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class CarDealershipServiceTest {
    
    @Autowired
    CarDealershipService service;
}
