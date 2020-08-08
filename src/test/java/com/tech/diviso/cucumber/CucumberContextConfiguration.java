package com.tech.diviso.cucumber;

import com.tech.diviso.JhipsterApp;
import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
@ContextConfiguration(classes = JhipsterApp.class)
public class CucumberContextConfiguration {
    @Before
    public void setup_cucumber_spring_context(){
    System.out.println("################  spring context is running #############");
    }
}
