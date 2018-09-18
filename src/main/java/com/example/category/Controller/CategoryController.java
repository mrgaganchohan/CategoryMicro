package com.example.category.Controller;

import com.example.category.Entity.DTO.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.category.Entity.Category;
import com.example.category.Repositories.CategoryRepo;

import java.util.List;

@CrossOrigin  //Access-control-allow-origin
@RestController
@RequestMapping(path="/category")
public class CategoryController {
    @Autowired
    private CategoryRepo categoryRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewCategory(CategoryDTO category) {
            Category n = new Category();
            n.setName(category.getName());
            categoryRepository.save(n);
            return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path="/deleteAll")
    public @ResponseBody String delAllUsers() {
        categoryRepository.deleteAll();
        return "All Deleted";
    }
    @GetMapping(path="/delete/{name}")
    public @ResponseBody void delUser(@PathVariable ("name") String name) {
        categoryRepository.deleteByName(name);

    }
    @GetMapping(path = "/name/{text}")
    public ResponseEntity searchByName(@PathVariable final String text){
        List<Category> searchName = categoryRepository.findByName(text);
        return new ResponseEntity(searchName, HttpStatus.OK);
    }

}