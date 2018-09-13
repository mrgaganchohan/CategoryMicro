package com.example.category.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public @ResponseBody String addNewUser (Category category) {
        Category n = new Category();
        n.setName(category.getName());

        categoryRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllUsers() {
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
    public List<Category> searchName(@PathVariable final String text) {
        return categoryRepository.findByName(text);
    }



}
