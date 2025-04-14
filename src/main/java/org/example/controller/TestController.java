package org.example.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@ConditionalOnProperty(prefix = "test.controller", value = "enabled")
@RestController
@RequestMapping("${test.controller.url}")
@Setter(onMethod = @__({@Autowired(required = false)}))
@Slf4j
public class TestController {

    @RequestMapping(
            value = "/hello",
            method = RequestMethod.GET
    )
    public ResponseEntity<String> testHello(@RequestParam("name") String name) {
        String res = "Hello " + name;
        return ResponseEntity.ok(res);
    }
}
