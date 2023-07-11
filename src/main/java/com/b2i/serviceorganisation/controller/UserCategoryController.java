package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.dto.request.UserCategoryRequest;
import com.b2i.serviceorganisation.service.UserCategoryServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user-categories")
public class UserCategoryController {

    private final UserCategoryServiceImplementation userCategoryServiceImplementation;

    public UserCategoryController(UserCategoryServiceImplementation userCategoryServiceImplementation) {
        this.userCategoryServiceImplementation = userCategoryServiceImplementation;
    }

    // CREATE USER CATEGORY
    @PostMapping
    ResponseEntity<Object> createUserCategory(@RequestBody UserCategoryRequest userCategoryRequest){
        return this.userCategoryServiceImplementation.createCategory(userCategoryRequest);
    }

    // UPDATE USER CATEGORY
    @PutMapping("/{idCategory}")
    ResponseEntity<Object> updateUserCategory(@RequestBody UserCategoryRequest userCategoryRequest, @PathVariable Long idCategory){
        return this.userCategoryServiceImplementation.updateCategory(userCategoryRequest, idCategory);
    }

    // DELETE CATEGORY BY ID
    @DeleteMapping("/{idCategory}")
    ResponseEntity<Object> deleteUserCategory(@PathVariable Long idCategory){
        return this.userCategoryServiceImplementation.deleteCategory(idCategory);
    }

    // FIND ALL CATEGORIES
    @GetMapping
    ResponseEntity<Object> getAllUsersCategory(){
        return this.userCategoryServiceImplementation.findAllCategory();
    }

    // FIND CATEGORY BY ID
    @GetMapping("/{idCategory}")
    public ResponseEntity<Object> findUsersCategoryById(@PathVariable("idCategory") Long idCategory) {
        return userCategoryServiceImplementation.findCategoryById(idCategory);
    }

    @GetMapping("search")
    public ResponseEntity<Object> findUserCategoryByName(@RequestParam String name) {
        return userCategoryServiceImplementation.findUserCategoryByName(name);
    }
}
