package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.dto.request.UserTypeRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.UserType;
import com.b2i.serviceorganisation.repository.UserTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeServiceImplementation implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public UserTypeServiceImplementation(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public ResponseEntity<Object> createType(UserTypeRequest userTypeRequest) {
        try {
            //CHECK IF TYPE NAME IS NOT NULL
            Utils.checkStringValues(userTypeRequest.getLabel(), "type name");
            UserType userType = UserType.builder().
                    label(userTypeRequest.getLabel()).
                    description(userTypeRequest.getDescription())
                    .build();
            //SAVE AND RETURN
            return ResponseHandler.generateResponse("This type has been saved !", HttpStatus.CREATED, this.userTypeRepository.save(userType));
        }catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> deleteType(Long id) {
        // GET TYPE
        Optional<UserType> type = userTypeRepository.findById(id);

        // TRY DELETING TYPE
        if (!type.isPresent()) {
            return ResponseHandler.generateResponse("This type doesn't exist or has already been deleted",
                    HttpStatus.NOT_FOUND, null);
        }

        try {
            System.out.println("This type has been deleted properly !");
            this.userTypeRepository.delete(type.get());
            return ResponseHandler.generateResponse("This type has been deleted properly !", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> updateType(UserTypeRequest userTypeRequest, Long id) {
        Optional<UserType> type = this.userTypeRepository.findById(id);
        if(!type.isPresent()){
            return ResponseHandler.generateResponse("This type doesn't exist",
                    HttpStatus.NOT_FOUND, null);
        }
        try {
            Utils.checkStringValues(userTypeRequest.getLabel(), "type name");
            type.get().setLabel(userTypeRequest.getLabel());
            type.get().setDescription(userTypeRequest.getDescription());
            //UPDATE AND RETURN
            return ResponseHandler.generateResponse("This type has been saved !", HttpStatus.CREATED, this.userTypeRepository.save(type.get()));
        }catch (Exception e){
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findAllTypes() {
        // GET ALL
        List<UserType> userTypes = userTypeRepository.findAll();

        try {
            if(userTypes.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty !");
            }
            return ResponseHandler.generateResponse("Types list", HttpStatus.OK, userTypes);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findTypeById(Long id) {
        // GET TYPE BY ID
        Optional<UserType> userType = userTypeRepository.findById(id);
        try {
            return userType.map(type -> ResponseHandler.generateResponse("user type : " + id, HttpStatus.OK, type)).orElseGet(() -> ResponseHandler.generateResponse("user type not found ", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findUserTypeByLabel(String label) {

        try {
            List<UserType> userTypes = userTypeRepository.findUserTypeByLabel(label);
            if (userTypes.isEmpty()) {
                return ResponseHandler.generateResponse("UserTypes list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("UserTypes list", HttpStatus.OK, userTypes);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public Long countAllUserTypes() {
        return userTypeRepository.count();
    }
}
