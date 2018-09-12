package com.example.category.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.category.Entity.Category;
import com.example.category.Repositories.CategoryRepo;

@CrossOrigin  //Access-control-allow-origin
@RestController
@RequestMapping(path="/category")
public class CategoryController {
    @Autowired
    private CategoryRepo userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (Category category) {
        Category n = new Category();
        n.setName(category.getName());

        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/deleteAll")
    public @ResponseBody String delAllUsers() {
        userRepository.deleteAll();
        return "All Deleted";
    }
    @GetMapping(path="/delete/{name}")
    public @ResponseBody void delUser(@PathVariable ("name") String name) {
        userRepository.deleteByName(name);

    }


}
