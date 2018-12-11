package com.cong.springx.mockmvc;

import com.cong.springx.SpringxApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringxApplication.class)
@AutoConfigureMockMvc
public class MockMvcTest {

    @Autowired
    private MockMvc  mockMvc;

    @Test
    public void  apiTest ()  {

        MvcResult mvcResult = null;
        try {
            mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/v1.0/hi")).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int status = mvcResult.getResponse().getStatus();
        System.out.println(status);

    }
}
