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

import java.util.ArrayList;
import java.util.List;


@CrossOrigin  //Access-control-allow-origin
@RestController
@RequestMapping(path="/category")
public class SubCategoryController {



    @Autowired
    private SubCatRepo subcatRepo;

    @Autowired
    private CategoryRepo categoryRepository;

    private static final String CAT = "Category = ";
    private static final String AE = ", Already Exists";
    private static final String DNE = ", Does Not Exist";
    private static final String SUB = "Sub-Category = ";


    @PostMapping(path = "/add-subcategory/{catName}", consumes = "application/json")
    public ResponseEntity<SubCategoryDTO> addSubCat(@RequestBody SubCategoryDTO subcategory, @PathVariable ("catName") final String catName) {
        Category n = categoryRepository.findByCatName(catName);
        if (n == null) {
            return new ResponseEntity(CAT + catName + DNE, HttpStatus.CONFLICT);
        }
        SubCategory s = new SubCategory();
        String subCatName = subcategory.getName();

        String satName = subcategory.getStatus();

        s.setName(subCatName);
        SubCategory exists = subcatRepo.findBySubCatName(subCatName);
        if (exists != null){
            return new ResponseEntity(SUB + subCatName + AE, HttpStatus.CONFLICT);
        }
        s.setStatus(subStat);
        s.setCategory(n);
        s.setStatus(satName);
        subcatRepo.save(s);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/sub-category/{catName}")
    public ResponseEntity findAllSubCat(@PathVariable String catName){ //Electronics
        Category cid = categoryRepository.findByCatName(catName);
        List<SubCategory> findAllSubCat = subcatRepo.findByCategory(cid);
        return new ResponseEntity<>(findAllSubCat, HttpStatus.OK);
    }


    @DeleteMapping(path="/sub-category/delete/{name}")
    public ResponseEntity<String>  delSubCategory(@PathVariable ("name") final String name) {
        SubCategory exists = subcatRepo.findBySubCatName(name);
        if (exists==null){
            return new ResponseEntity<>(SUB + name + DNE, HttpStatus.CONFLICT);
        }
        subcatRepo.deleteByName(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path="/sub-category/deleteAll")
    public ResponseEntity<String>  delAllSubCategory() {
        subcatRepo.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/sub-category/search/{text}")
    public ResponseEntity searchByName(@PathVariable final String text){
        List<SubCategory> searchName = subcatRepo.findByName(text);
        return new ResponseEntity<>(searchName, HttpStatus.OK);
    }

    @GetMapping(path = "/sub-category/{id}")
    public ResponseEntity<SubCategory> getSubCategoryId(@PathVariable ("id") final int id) {
       List<SubCategory> cat = subcatRepo.findSubCategoriesByCategoryCatId(id);
//        if (cat==null){
//            return new ResponseEntity(CAT  + DNE, HttpStatus.CONFLICT);
//        }

//        List<Integer> temp = new ArrayList<>();
//        for (int i =0; i< cat.getSubcategory().size(); i++){
//            temp.add(cat.getSubcategory().get(i).getSubId());
//        }
        return new ResponseEntity(cat, HttpStatus.OK);
    }

    @GetMapping(path = "/sub-")



    @PutMapping(path = "/sub-category/update/{catName}")
    public ResponseEntity<String> updateSubCategory(@PathVariable ("catName") final String catName, @RequestBody SubCategoryDTO subcategory){
        SubCategory subCat = subcatRepo.findBySubCatName(catName);
        if (subCat==null){
            return new ResponseEntity<>(SUB + catName + DNE, HttpStatus.CONFLICT);
        }
        subCat.setName(subcategory.getName());
        subCat.setStatus(subcategory.getStatus());
        subcatRepo.save(subCat);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
