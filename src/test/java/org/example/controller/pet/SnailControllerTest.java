package org.example.controller.pet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.SpringApplicationTest;
import org.example.model.pet.MucusLevel;
import org.example.model.pet.Snail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringApplicationTest
@AutoConfigureMockMvc
@Sql(scripts = "/db/pet/snail_init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/db/pet/snail_clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class SnailControllerTest {

    private static final String SNAIL_ID_1 = "snail_test_id_1";
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mvc;

    @Test
    void getOneTest() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(SnailController.PATH + SNAIL_ID_1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Snail snail = objectMapper.readValue(contentAsString, Snail.class);
        Assertions.assertEquals(snail.getAge(), 5);
        Assertions.assertEquals(snail.getMucusLevel(), MucusLevel.HIGH);
        Assertions.assertNotNull(snail.getFullName());
    }
}
