package org.example.integration.bikini.validation;

import org.springframework.cloud.openfeign.FeignClient;
import www.ru.bikini_validation.api.swagger.api.BikiniValidationApi;

@FeignClient(
        name = "bikini-validation",
        url = "${integration.bikini.validation.url}"
)
public interface BikiniValidationClient extends BikiniValidationApi {
}
