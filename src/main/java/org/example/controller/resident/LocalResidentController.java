package org.example.controller.resident;

import lombok.AllArgsConstructor;
import org.example.model.resident.LocalResident;
import org.example.service.resident.LocalResidentStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(LocalResidentController.PATH)
public class LocalResidentController {

    public static final String PATH = "/api/resident/local/";

    private LocalResidentStoreService storeService;

    //todo RBS-8 create and use filter

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/save",
            consumes = "application/json"
    )
    public ResponseEntity<Void> save(@RequestBody LocalResident dto) {
        storeService.save(dto);
        return ResponseEntity.ok().build();
    }
}
