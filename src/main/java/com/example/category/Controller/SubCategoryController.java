package com.example.category.Controller;

import com.example.category.Entity.DTO.SubCategoryDTO;
import com.example.category.Entity.SubCategory;
import com.example.category.Repositories.CategoryRepo;
import com.example.category.Repositories.SubCatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.category.Entity.Category;

import java.util.List;


@CrossOrigin  //Access-control-allow-origin
@RestController
@RequestMapping(path="/category")
public class SubCategoryController {



    @Autowired
    private SubCatRepo subcatRepo;

    @Autowired
    private CategoryRepo categoryRepository;

//
    @PostMapping(path = "/add-subcategory/{catName}", consumes = "application/json")
    public ResponseEntity<SubCategoryDTO> addSubCat(@RequestBody SubCategoryDTO subcategory, @PathVariable ("catName") final String catName){
        Category n = categoryRepository.findByCatName(catName);
        if (n == null){
            return new ResponseEntity("Category = " + catName + ", Does Not Exist", HttpStatus.CONFLICT);
        }
        SubCategory s = new SubCategory();
        String subCatName = subcategory.getName();
        s.setName(subCatName);
        SubCategory exists = subcatRepo.findBySubCatName(subCatName);
        if (exists != null){
            return new ResponseEntity("Sub-Category = " + subCatName + ", Already Exists", HttpStatus.CONFLICT);
        }
        s.setCategory(n);
        subcatRepo.save(s);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/Sub-Category/{catName}")
    public ResponseEntity findAllSubCat(@PathVariable String catName){ //Electronics
        Category cid = categoryRepository.findByCatName(catName);
        List<SubCategory> findAllSubCat = subcatRepo.findByCategory(cid);
        return new ResponseEntity<>(findAllSubCat, HttpStatus.OK);
    }

    @DeleteMapping(path="/Sub-Category/delete/{name}")
    public ResponseEntity<Void>  delSubCategory(@PathVariable ("name") final String name) {
        SubCategory exists = subcatRepo.findBySubCatName(name);
        if (exists==null){
            return new ResponseEntity("Sub-Category = " + name + ", Does Not Exist", HttpStatus.CONFLICT);
        }
        subcatRepo.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path="/Sub-Category/deleteAll")
    public ResponseEntity<Void>  delAllSubCategory() {
        subcatRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/Sub-Category/search/{text}")
    public ResponseEntity searchByName(@PathVariable final String text){
        List<SubCategory> searchName = subcatRepo.findByName(text);
        return new ResponseEntity<>(searchName, HttpStatus.OK);
    }



}
