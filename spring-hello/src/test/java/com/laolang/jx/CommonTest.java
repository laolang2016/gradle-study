package com.laolang.jx;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@Slf4j
public class CommonTest {
    @Test
    public void appHasAGreeting() {
        log.info("common test");
        System.out.println("common test");
        SpringHelloApp classUnderTest = new SpringHelloApp();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
