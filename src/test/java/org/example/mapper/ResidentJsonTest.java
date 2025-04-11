package org.example.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import www.ru.bikini_bottom.api.swagger.model.ResidentDtoBody;

import java.nio.file.Files;
import java.nio.file.Paths;

class ResidentJsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String PATH = "src/test/resources/resident/localResidentToSave.json";

    @Test
    void jsonToResidentBodyDtoTest() throws Exception{
        String json = Files.readString(Paths.get(PATH));

        ResidentDtoBody dtoBody = objectMapper.readValue(json, ResidentDtoBody.class);
        Assertions.assertNotNull(dtoBody.getType());

    }
}
