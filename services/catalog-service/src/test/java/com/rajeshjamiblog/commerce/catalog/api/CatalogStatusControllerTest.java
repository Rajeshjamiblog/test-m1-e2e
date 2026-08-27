package com.rajeshjamiblog.commerce.catalog.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CatalogStatusController.class)
class CatalogStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsTheCatalogServiceStatus() throws Exception {
        var response = mockMvc.perform(get("/api/v1/catalog/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.service").value("catalog-service"))
                .andExpect(jsonPath("$.status").value("available"))
                .andReturn();

        assertThat(response.getResponse().getContentType()).startsWith("application/json");
    }
}
