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
        SubCategory s = new SubCategory();
        s.setName(subcategory.getName());
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



}
