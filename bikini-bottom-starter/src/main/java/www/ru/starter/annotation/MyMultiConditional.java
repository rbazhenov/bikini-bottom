package www.ru.starter.annotation;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Conditional(OnMyMultiConditional.class)
public @interface MyMultiConditional {
}
