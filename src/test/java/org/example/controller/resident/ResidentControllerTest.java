package org.example.controller.resident;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;

//аналогично
//@WithMockUser
//@SpringApplicationTest
//@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestPropertySource(locations = "classpath:application-test.yaml")
class ResidentControllerTest {

    private static final String API = "/bikini-bottom/resident";
    private static final String PATH = "src/test/resources/resident/localResidentToSave.json";

    @Autowired
    private MockMvc mvc;

    @Test
    void saveLocalResidentTest() throws Exception {
        String json = Files.readString(Paths.get(PATH));

        mvc.perform(MockMvcRequestBuilders.post(API + "/save").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk()).andExpect(content().json(json));
    }
}
