package www.ru.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import www.ru.starter.annotation.ConditionalOnProduction;

@Configuration
@EnableConfigurationProperties(VoronProperties.class)
public class MyConfiguration {

    @Bean
    @ConditionalOnProduction
    @ConfigurationProperties("voron.address")
    @ConditionalOnMissingBean
    public VoronListener voronListener(VoronProperties properties){
        return new VoronListener(properties);
    }
}
