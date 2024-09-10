package com.laolang.jx;

import com.laolang.jx.module.system.dict.logic.SysDictLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        SysDictLogic sysDictLogic = context.getBean(SysDictLogic.class);
        log.info("sysDictLogic typeList:{}", sysDictLogic.dictTypeList());
    }
}
