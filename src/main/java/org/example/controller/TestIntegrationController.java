package org.example.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.integration.bikini.validation.BikiniValidationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import www.ru.bikini_validation.api.swagger.model.ResidentCheckResponse;

@ConditionalOnProperty(prefix = "test.controller", value = "enabled")
@RestController
@RequestMapping("${test.controller.url}")
@Setter(onMethod = @__({@Autowired(required = false)}))
public class TestIntegrationController {

    private BikiniValidationClient validationClient;

    @RequestMapping(
            value = "/hello",
            method = RequestMethod.GET
    )
    public ResponseEntity<String> testHello(@RequestParam("name") String name) {
        String res = "Hello " + name;
        return ResponseEntity.ok(res);
    }

    @RequestMapping(
            value = "/resident/check",
            method = RequestMethod.POST
    )
    public ResponseEntity<ResidentCheckResponse> testResidentCheck(@RequestParam("id") String id) {
        return validationClient.checkResident(id);
    }
}
