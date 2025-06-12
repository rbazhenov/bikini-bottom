package www.ru.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import www.ru.starter.annotation.ConditionalOnProduction;

@RequiredArgsConstructor
public class VoronListener implements ApplicationListener<ContextRefreshedEvent> {

    private final VoronProperties voronProperties;

    @Override
    @ConditionalOnProduction
    public void onApplicationEvent(ContextRefreshedEvent event) {
        voronProperties.getAddress().forEach(address -> System.out.println("Ворон полетел... в " + address));
    }
}
