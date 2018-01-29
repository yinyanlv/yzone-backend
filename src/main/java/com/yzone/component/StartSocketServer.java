package com.yzone.component;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.yzone.util.SocketServer;

@Component
public class StartSocketServer implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                SocketServer.run();
            }
        }).start();
    }
}
