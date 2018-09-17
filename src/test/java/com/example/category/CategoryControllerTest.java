package com.example.category;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    public String emptyDB = "[]";
    public String jsonS = "[{\"catId\":1,\"name\":\"elec\"}]";


    @Autowired
    private MockMvc mockMvc;

    // Test database connection


    // Test empty database
    @Test
    public void shouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/category/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(emptyDB)));
    }

    // Test add category

}



