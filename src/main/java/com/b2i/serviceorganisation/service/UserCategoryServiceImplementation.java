package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.dto.request.UserCategoryRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.UserCategory;
import com.b2i.serviceorganisation.repository.UserCategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCategoryServiceImplementation implements UserCategoryService{

    private final UserCategoryRepository userCategoryRepository;

    public UserCategoryServiceImplementation(UserCategoryRepository userCategoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
    }

    @Override
    public ResponseEntity<Object> createCategory(UserCategoryRequest userCategoryRequest) {
        try {
            //CHECK IF CATEGORY NAME IS NOT NULL
            Utils.checkStringValues(userCategoryRequest.getName(), "category name");
            UserCategory userCategory = UserCategory.builder().
                    name(userCategoryRequest.getName()).
                    description(userCategoryRequest.getDescription())
                    .build();
            //SAVE AND RETURN
            return ResponseHandler.generateResponse("This category has been saved !", HttpStatus.CREATED, this.userCategoryRepository.save(userCategory));
        }catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> deleteCategory(Long id) {
        // GET CATEGORY
        Optional<UserCategory> category = userCategoryRepository.findById(id);

        // TRY DELETING CATEGORY
        if (!category.isPresent()) {
            return ResponseHandler.generateResponse("This category doesn't exist or has already been deleted",
                    HttpStatus.NOT_FOUND, null);
        }

        try {
            this.userCategoryRepository.delete(category.get());
            return ResponseHandler.generateResponse("This category has been deleted properly !", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> updateCategory(UserCategoryRequest userCategoryRequest, Long id) {
        Optional<UserCategory> category = this.userCategoryRepository.findById(id);
        if(!category.isPresent()){
            return ResponseHandler.generateResponse("This category doesn't exist",
                    HttpStatus.NOT_FOUND, null);
        }
        try {
            Utils.checkStringValues(userCategoryRequest.getName(), "category name");
            category.get().setName(userCategoryRequest.getName());
            category.get().setDescription(userCategoryRequest.getDescription());
            //UPDATE AND RETURN
            return ResponseHandler.generateResponse("This type has been saved !", HttpStatus.CREATED, this.userCategoryRepository.save(category.get()));
        }catch (Exception e){
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findAllCategory() {
        // GET ALL
        List<UserCategory> userCategories = userCategoryRepository.findAll();

        try {
            if(userCategories.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty !");
            }
            return ResponseHandler.generateResponse("Categories list", HttpStatus.OK, userCategories);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findCategoryById(Long id) {
        // GET CATEGORY BY ID
        Optional<UserCategory> userCategory = userCategoryRepository.findById(id);
        try {
            return userCategory.map(type -> ResponseHandler.generateResponse("user category : " + id, HttpStatus.OK, type)).orElseGet(() -> ResponseHandler.generateResponse("user category not found ", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findUserCategoryByName(String name) {

        try {
            List<UserCategory> userCategories = userCategoryRepository.findUserCategoryByName(name);
            if (userCategories.isEmpty()) {
                return ResponseHandler.generateResponse("UserCategories list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("UserCategories list", HttpStatus.OK, userCategories);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public Long countAllUserCategories() {
        return userCategoryRepository.count();
    }
}
