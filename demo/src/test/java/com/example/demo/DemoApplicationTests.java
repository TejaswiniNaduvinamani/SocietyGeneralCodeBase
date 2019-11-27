package com.example.demo;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@Transactional
@Rollback
@SpringBootTest(classes = DemoApplicationRunnerTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoApplicationTests {
	
	public MockMvc mockMvc;
	
	@Autowired
	public  WebApplicationContext webAppContext;

	@Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.webAppContext).build();
    }

}
