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
    public ResponseEntity<Category>  addNewCategory (CategoryDTO category) {
        Category n = new Category();
        n.setName(category.getName());
        categoryRepository.save(n);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @DeleteMapping(path="/deleteAll")
    public ResponseEntity<Void>  delAllCategory() {
        categoryRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{name}")
    public ResponseEntity<Void>  delCategory(@PathVariable ("name") final String name) {
        categoryRepository.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping(path = "/search/{text}")
    public ResponseEntity searchByName(@PathVariable final String text){
        List<Category> searchName = categoryRepository.findByName(text);
        return new ResponseEntity<>(searchName, HttpStatus.OK);
    }

}
