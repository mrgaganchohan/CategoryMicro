package com.example.category.Controller;

import com.example.category.Entity.DTO.CategoryDTO;
import com.example.category.Entity.SubCategory;
import com.example.category.Repositories.SubCatRepo;
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

    @Autowired
    private SubCatRepo subcatRepo;

    private static final String CAT = "Category = ";
    private static final String AE = ", Already Exists";
    private static final String DNE = ", Does Not Exist";


    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<String> createCategory (@RequestBody CategoryDTO category){
        Category n = new Category();
        String catName = category.getName();
        String stat = category.getStatus();
        Category exists = categoryRepository.findByCatName(catName);
        if (exists != null){
            return new ResponseEntity<>(CAT + catName + AE, HttpStatus.CONFLICT);
        }
        n.setName(catName);
        n.setStatus(stat);
        categoryRepository.save(n);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable ("id") final int id){
        Category cat = categoryRepository.findByCatId(id);
        if (cat.getStatus().equals("Block")){
            return new ResponseEntity("This Category is Blocked ", HttpStatus.CONFLICT);
        }else {
            return new ResponseEntity(cat, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/name/{catName}")
    public ResponseEntity<Category> getIDbyName(@PathVariable ("catName") final String catName){
        int cat = categoryRepository.findId(catName);
        List<SubCategory> subCat = subcatRepo.findSubCategoriesByCategoryCatId(cat);
        return new ResponseEntity(subCat,HttpStatus.OK);
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
        List<Category> searchName = categoryRepository.findBySName(text);
        return new ResponseEntity<>(searchName, HttpStatus.OK);
    }

    @PutMapping(path = "/update/{catName}")
    public ResponseEntity<String> updateCategory(@PathVariable ("catName") final String catName, @RequestBody CategoryDTO category){
        Category c = categoryRepository.findByCatName(catName);
        if (c==null){
            return new ResponseEntity<>(CAT + catName + DNE, HttpStatus.CONFLICT);
        }
        c.setName(category.getName());
        c.setStatus(category.getStatus());
        categoryRepository.save(c);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
