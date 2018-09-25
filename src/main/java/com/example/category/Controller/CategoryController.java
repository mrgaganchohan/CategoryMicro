package com.example.category.Controller;

import com.example.category.Entity.DTO.CategoryDTO;
import com.example.category.Entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.category.Entity.Category;
import com.example.category.Repositories.CategoryRepo;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin  //Access-control-allow-origin
@RestController
@RequestMapping(path="/category")
public class CategoryController {


    @Autowired
    private CategoryRepo categoryRepository;

    private static final String CAT = "Category = ";
    private static final String AE = ", Already Exists";
    private static final String DNE = ", Does Not Exist";


    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> createCategory (@RequestBody CategoryDTO category){
        Category n = new Category();
        String catName = category.getName();
        Category exists = categoryRepository.findByCatName(catName);
        if (exists != null){
            return new ResponseEntity<>(CAT + catName + AE, HttpStatus.CONFLICT);
        }
        n.setName(category.getName());
        categoryRepository.save(n);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }


    @GetMapping(path = "/subid/{catName}")
    public ResponseEntity<Category> getCategoryId(@PathVariable ("catName") final String catName) {
        Category cat = categoryRepository.findByCatName(catName);
        if (cat==null){
            return new ResponseEntity(CAT + catName + DNE, HttpStatus.CONFLICT);
        }
        List<Integer> temp = new ArrayList<>();
        for (int i =0; i< cat.getSubcategory().size(); i++){
            temp.add(cat.getSubcategory().get(i).getSubId());
        }
        return new ResponseEntity(temp, HttpStatus.OK);
    }

    @DeleteMapping(path="/deleteAll")
    public ResponseEntity<Void>  delAllCategory() {
        categoryRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path="/delete/{name}")
    public ResponseEntity<String>  delCategory(@PathVariable ("name") final String name) {
        Category exists = categoryRepository.findByCatName(name);
        if (exists==null){
            return new ResponseEntity<>(CAT + name + DNE, HttpStatus.CONFLICT);
        }
        categoryRepository.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @GetMapping(path = "/search/{text}")
    public ResponseEntity searchByName(@PathVariable final String text){
        List<Category> searchName = categoryRepository.findByName(text);
        return new ResponseEntity<>(searchName, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{catName}")
    public ResponseEntity<String> updateCategory(@PathVariable ("catName") final String catName, @RequestBody CategoryDTO category){
        Category c = categoryRepository.findByCatName(catName);
        if (c==null){
            return new ResponseEntity<>(CAT + catName + DNE, HttpStatus.CONFLICT);
        }
        c.setName(category.getName());
        categoryRepository.save(c);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
