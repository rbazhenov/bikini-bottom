package org.example.integration.bikini.validation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.model.resident.ResidentCheckAnswer;
import org.example.properties.ValidationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@ConditionalOnProperty(
        prefix = "integration.validation.kafka",
        name = "enabled",
        havingValue = "true"
)
@AllArgsConstructor
public class ValidationKafkaConfig {

    private final ValidationProperties properties;

    static ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean("validationConsumerFactory")
    public ConsumerFactory<String, ResidentCheckAnswer> validationConsumerFactory() {
        JsonDeserializer<ResidentCheckAnswer> deserializer = new JsonDeserializer<>(objectMapper());
        deserializer.addTrustedPackages(ResidentCheckAnswer.class.getPackageName(), ErrorHandlingDeserializer.class.getPackageName());
        deserializer.ignoreTypeHeaders();
        deserializer.setTypeResolver((topic, data, headers)
                -> TypeFactory.defaultInstance().constructFromCanonical(ResidentCheckAnswer.class.getCanonicalName()));

        return new DefaultKafkaConsumerFactory<>(
                propsConfigs(),
                new ErrorHandlingDeserializer<>(new StringDeserializer()),
                new ErrorHandlingDeserializer<>(deserializer));
    }

    @Bean("validationContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, ResidentCheckAnswer> validationContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ResidentCheckAnswer> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(validationConsumerFactory());
        return factory;
    }

    Map<String, Object> propsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getServer());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, properties.getClientId());
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        props.put(ProducerConfig.RETRIES_CONFIG, 5);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean("validationErrorHandler")
    public KafkaListenerErrorHandler validationErrorHandler() {
        return (message, exception) -> {
            if (log.isDebugEnabled()) {
                log.error("Mock error validation kafka exception: {}", message, exception);
            } else {
                try {
                    log.debug(message.toString());
                    log.debug(message.getClass().toString());
                    log.debug(message.getHeaders().toString());
                    log.debug(message.getPayload().toString());
                } catch (Exception e) {
                    log.debug(e.getMessage(), e);
                }
                log.error("Mock error validation kafka exception: {} ({})", message, exception.getMessage());
            }
            return null;
        };
    }
}
