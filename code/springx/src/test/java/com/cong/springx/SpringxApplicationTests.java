package com.cong.springx;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringxApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    public void  test1() {
        System.out.println( "test 111");

        TestCase.assertEquals(1,1);
    }

}
