package com.b2i.serviceorganisation.controller;

import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.dto.request.PostRequest;
import com.b2i.serviceorganisation.service.PostServiceImplementation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    private final PostServiceImplementation postServiceImplementation;

    public PostController(PostServiceImplementation postServiceImplementation) {
        this.postServiceImplementation = postServiceImplementation;
    }


    // CREATE
   /* @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostRequest postRequest) {
        return postServiceImplementation.createPost(postRequest);
    }*/

    // CREATE A POSTE IN THE CLUB
    @PostMapping
    public ResponseEntity<Object> createPost(@RequestBody PostRequest postRequest, @RequestParam Long idClub, @RequestParam Long idArea, @RequestParam Long idCenter, @RequestParam Long idMainOffice) {
        return postServiceImplementation.createPost(postRequest, idClub, idArea, idCenter, idMainOffice);
    }

/*    // CREATE A POSTE IN THE AREA
    @PostMapping("area")
    public ResponseEntity<Object> createPostInArea(@RequestBody PostRequest postRequest, @RequestParam Long idArea) {
        return postServiceImplementation.createPostInArea(postRequest, idArea);
    }

    // CREATE A POSTE IN THE CENTRE
    @PostMapping("center")
    public ResponseEntity<Object> createPostInCenter(@RequestBody PostRequest postRequest, @RequestParam Long idCenter) {
        return postServiceImplementation.createPostInCenter(postRequest, idCenter);
    }

    // CREATE A POSTE IN THE MAIN OFFICE
    @PostMapping("center")
    public ResponseEntity<Object> createPostInMainOffice(@RequestBody PostRequest postRequest, @RequestParam Long idMainOffice) {
        return postServiceImplementation.createPostInMainOffice(postRequest, idMainOffice);
    }*/


    // FIND ALL
    @GetMapping
    public ResponseEntity<Object> findAllPosts() {
        return postServiceImplementation.findAllPosts();
    }


    // UPDATE POST
    @PutMapping("/{idPost}")
    public ResponseEntity<Object> updatePost(@PathVariable("idPost") Long idPost, @RequestBody PostRequest postRequest) {
        return postServiceImplementation.updatePost(idPost, postRequest);
    }


    // DELETE POST
    @DeleteMapping("/{idPost}")
    public ResponseEntity<Object> deletePost(@PathVariable("idPost") Long idPost) {
        return postServiceImplementation.deletePost(idPost);
    }


    // FIND POST BY ID
    @GetMapping("/{idPost}")
    public ResponseEntity<Object> findPostById(@PathVariable("idPost") Long idPost) {
        return postServiceImplementation.findPostById(idPost);
    }

    // FIND LIST POSTS OF A ORGANISATION TYPE
    @GetMapping("/find/{organisationLevel}")
    public ResponseEntity<Object> findAllPostsByOrganisationLevel(@PathVariable("organisationLevel") OrganisationLevelEnum organisationLevel) {
        return postServiceImplementation.findAllPostsByOrganisationLevel(organisationLevel);
    }

    @GetMapping("/findMainOffice/{idMainOffice}")
    public ResponseEntity<Object> finAllPostByIdMainOffice(@PathVariable("idMainOffice") Long idMainOffice) {
        return postServiceImplementation.finAllPostByIdMainOffice(idMainOffice);
    }

    @GetMapping("/findCenter/{idCenter}")
    public ResponseEntity<Object> finAllPostByIdCenter(@PathVariable("idCenter") Long idCenter) {
        return postServiceImplementation.finAllPostByIdCenter(idCenter);
    }

    @GetMapping("/findArea/{idArea}")
    public ResponseEntity<Object> finAllPostByIdArea(@PathVariable("idArea") Long idArea) {
        return postServiceImplementation.finAllPostByIdArea(idArea);
    }

    @GetMapping("/findClub/{idClub}")
    public ResponseEntity<Object> finAllPostByIdClub(@PathVariable("idClub") Long idClub) {
        return postServiceImplementation.finAllPostByIdClub(idClub);
    }

    @PatchMapping("add-operator")
    public ResponseEntity<Object> addOperatorToPost(@RequestParam("idOperator") Long idOperator,@RequestParam("idPost") Long idPost, @RequestParam("idFunction") Long idFunction){
        return postServiceImplementation.addOperatorToPost(idOperator, idPost, idFunction);
    }

    @PatchMapping("remove-operator")
    public ResponseEntity<Object> removeAnOperatorFromAPost(@RequestParam("idPost") Long idPost,@RequestParam("idOperator") Long idOperator){
        return postServiceImplementation.removeAnOperatorFromAPost(idPost, idOperator);
    }

    @GetMapping("search")
    public ResponseEntity<Object> findPostByName(@RequestParam String name) {
        return postServiceImplementation.findPostByName(name);
    }
}
