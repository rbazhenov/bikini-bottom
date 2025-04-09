package org.example;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@Lazy
@ActiveProfiles
@ContextConfiguration
public @interface SpringApplicationTest {
    @AliasFor(
            annotation = ActiveProfiles.class,
            attribute = "profiles") String[] activeProfiles() default {"test"};


    @AliasFor(
            annotation = SpringBootTest.class,
            attribute = "properties") String[] properties() default {""};
}
