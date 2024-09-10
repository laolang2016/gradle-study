package com.laolang.jx;

import lombok.extern.slf4j.Slf4j;

/**
 * gradle 单模块项目
 */
@Slf4j
public class SpringHelloApp {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        log.info("spring hello is running...");
        System.out.println(new SpringHelloApp().getGreeting());
    }
}
