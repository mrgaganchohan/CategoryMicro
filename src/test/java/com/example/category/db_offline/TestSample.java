package com.example.category.db_offline;


import com.example.category.Controller.CategoryController;
import com.example.category.Entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class TestSample {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryController service;

    @Test
    public void categoryArray() throws Exception{
        // Prepare 2 categories data
        Category electronic = new Category();
        electronic.setName("Electronic");
        electronic.setCatId(1);
//
        Category footwear  = new Category();
        footwear.setName("Footwear");
        footwear.setCatId(2);

        // Mock database
        List<Category> allCategory = new ArrayList<>();
        allCategory.add(electronic);
        allCategory.add(footwear);
        given(service.getAllCategories()).willReturn(allCategory);

        //
//        mvc.perform(get("/category/all")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))   // check if size of the data is correct
//                .andExpect(jsonPath("$[0].name").value(electronic.getName()));   // check the out put is the same as input
//                //.andExpect(jsonPath("$", hasSize(2))).andDo(MockMvcResultHandlers.print());   //print out the process


//        MvcResult result = mvc.perform(get("/category/all")
//                .contentType(MediaType.APPLICATION_JSON)).andReturn();
//        String content = result.getResponse().getContentAsString();
//
//        String expectOutput = "[{\"catId\":1,\"name\":\"Electronic\"},{\"catId\":2,\"name\":\"Footwear\"}]";
//        assertThat(content).isEqualTo(expectOutput);

    }

}
