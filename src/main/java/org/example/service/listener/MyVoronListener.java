package org.example.service.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import www.ru.starter.VoronListener;
import www.ru.starter.VoronProperties;

//@Component
public class MyVoronListener extends VoronListener {
    public MyVoronListener(VoronProperties voronProperties) {
        super(voronProperties);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("my voron flying logic");
    }
}
