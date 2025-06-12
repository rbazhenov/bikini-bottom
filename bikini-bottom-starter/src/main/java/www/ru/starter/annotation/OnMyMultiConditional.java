package www.ru.starter.annotation;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

public class OnMyMultiConditional extends AllNestedConditions {
    public OnMyMultiConditional() {
        super(ConfigurationPhase.REGISTER_BEAN);
    }

    @ConditionalOnProperty("voron.address")
    public static class R {}

    @ConditionalOnProperty(value = "voron.on", havingValue = "true")
    public static class C {}
}
