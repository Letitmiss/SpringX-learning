package com.cong.springx.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 支持的事件类型四种
    ApplicationStartedEvent
    ApplicationEnvironmentPreparedEvent
    ApplicationPreparedEvent
    ApplicationFailedEvent
 * ApplicationStartedEvent：spring boot启动开始时执行的事件
 *
 */
public class MyApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {

        System.out.println("MyApplicationStartedEventListener====");

    }
}
