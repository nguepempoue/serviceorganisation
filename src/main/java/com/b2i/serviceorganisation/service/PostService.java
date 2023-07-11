package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.dto.request.PostRequest;
import com.b2i.serviceorganisation.model.MainOffice;
import com.b2i.serviceorganisation.model.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    // CRUD OPERATIONS
    // CREATE A POST //
    ResponseEntity<Object> createPost(PostRequest postRequest, Long idClub, Long idArea, Long idCenter, Long idMainOffice);

    // CREATE A POST FOR A SPECIFIC LEVEL OF ORGANISATION //
    void createPostForLenelOrganisation(String name,  Integer maxNumberOfUser, String description, OrganisationLevelEnum organisationLevelEnum, Long id);

    void createPostForMainOffice(String name, Integer maxNumberOfUser, String description, OrganisationLevelEnum organisationLevelEnum, MainOffice mainOffice);

/*    // CREATE A POST IN THE AREA //
    ResponseEntity<Object> createPostInArea(PostRequest postRequest);

    // CREATE A POST IN THE CENTER //
    ResponseEntity<Object> createPostInCenter(PostRequest postRequest);

    // CREATE A POST IN THE MAIN OFFICE //
    ResponseEntity<Object> createPostInMainOffice(PostRequest postRequest);*/

    ResponseEntity<Object> findAllPosts();

    ResponseEntity<Object> findAllPostsByOrganisationLevel(OrganisationLevelEnum organisationLevelEnum);

    ResponseEntity<Object> updatePost(Long idPost, PostRequest postRequest);

    ResponseEntity<Object> deletePost(Long idPost);

    ResponseEntity<Object> finAllPostByIdMainOffice(Long idPost);

    ResponseEntity<Object> finAllPostByIdCenter(Long idCenter);

    ResponseEntity<Object> finAllPostByIdArea(Long idArea);

    ResponseEntity<Object> finAllPostByIdClub(Long idClub);

    ResponseEntity<Object> addOperatorToPost(Long idOperator, Long idPost, Long idFunction);

    ResponseEntity<Object> removeAnOperatorFromAPost(Long idPost, Long idOperator);

    ResponseEntity<Object>findPostByName(String name);

    ResponseEntity<Object> createApostForAclub(PostRequest postRequest, Long idClub);

    ResponseEntity<Object> createApostForAarea(PostRequest postRequest, Long idArea);

    ResponseEntity<Object> createApostForACenter(PostRequest postRequest, Long idCenter);

    ResponseEntity<Object> createApostForAmainOffcice(PostRequest postRequest, Long idMainOffice);


    // MORE OPERATIONS //
    ResponseEntity<Object> findPostById(Long idPost);

    Long countAll();

    Post savePost(Post post);
}
