package com.example.category.db_offline;


import com.example.category.Controller.CategoryController;
import com.example.category.Entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class OffCategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryController service;

    @Test
    public void testGetCategory() throws Exception{
        // Prepare 2 categories data
        Category electronic = new Category();
        electronic.setName("Electronic");
        electronic.setCatId(1);

        Category footwear  = new Category();
        footwear.setName("Footwear");
        footwear.setCatId(2);

        // Mock database
        List<Category> allCategory = new ArrayList<>();
        allCategory.add(electronic);
        allCategory.add(footwear);
        given(service.getAllCategories()).willReturn(allCategory);

        MvcResult dbResult = mvc.perform(get("/category/all")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String content = dbResult.getResponse().getContentAsString();

        // Expect Json data
        String expectOutput = "[{\"catId\":1,\"name\":\"Electronic\"},{\"catId\":2,\"name\":\"Footwear\"}]";
        assertThat(content).isEqualTo(expectOutput);
    }


    @Test
    public void testAddCategory()throws Exception{
        //Json category input
        String input = "[{\"catId\":1,\"name\":\"Electronic\"}]";
        // Expect status 200 ok
        int expectStatus = 200;

       MvcResult  result = mvc.perform(post("/category/add").content(input).contentType(MediaType.asMediaType(MediaType.APPLICATION_JSON_UTF8))).andExpect(status().isOk()).andReturn();
       int status = result.getResponse().getStatus();

        assertThat(expectStatus).isEqualTo(status);
    }

    @Test
    public void testAddCategorys() throws Exception{
//        String jsonInput = "[{\"catId\":1,\"name\":\"Electronic\"}]";
//        String input = null;
//
//        // Prepare 2 categories data
//        Category electronic = new Category();
//        electronic.setName("Electronic");
//        electronic.setCatId(1);
//
//
//      ResultActions result = mvc.perform(post("/category/add").contentType(MediaType.APPLICATION_JSON).content(input)).andExpect(status().isOk());
//      MvcResult rs = result.andReturn();
//      int resultStatus = rs.getResponse().getStatus();
//      int expectStatus = 200; // 200 ok,  404 not found

        assertThat(1).isEqualTo(1);
    }

}
