package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.dto.request.UserCategoryRequest;
import org.springframework.http.ResponseEntity;

public interface UserCategoryService {
    //CREATE THE TYPE OF CATEGORY
    ResponseEntity<Object> createCategory(UserCategoryRequest userCategoryRequest);
    //DELETE THE TYPE OF CATEGORY
    ResponseEntity<Object> deleteCategory(Long id);
    //UPDATE THE TYPE OF CATEGORY
    ResponseEntity<Object> updateCategory(UserCategoryRequest userCategoryRequest, Long id);
    // FIND ALL USER'S CATEGORY
    ResponseEntity<Object> findAllCategory();
    // FIND USER CATEGORY BY ID
    ResponseEntity<Object> findCategoryById(Long id);

    ResponseEntity<Object> findUserCategoryByName(String name);

    Long countAllUserCategories();
}
