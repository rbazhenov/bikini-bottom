package org.example.context;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.context.impl.CurrentUserImpl;
import org.example.context.impl.CurrentUserMock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = BpContext.class)
public class ContextConfiguration {

    @Value("${context.user.mock:#{null}}")
    private String userMock;

    @Bean("currentUser")
    @RequestScope
    public CurrentUser currentUser(HttpServletRequest request) {
        if (StringUtils.hasLength(userMock)) {
            log.warn("currentUser CurrentUserMock");
            return new CurrentUserMock();
        }

        return new CurrentUserImpl();
    }
}
