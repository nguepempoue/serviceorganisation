package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.dto.request.PostRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.*;
import com.b2i.serviceorganisation.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    private final ClubRepository clubRepository;
    private final AreaRepository areaRepository;
    private final CenterRepository centerRepository;
    private final MainOfficeRepository mainOfficeRepository;
    private final UserRepository userRepository;
    private final FunctionRepository functionRepository;

    public PostServiceImplementation(PostRepository postRepository, ClubRepository clubRepository, AreaRepository areaRepository, CenterRepository centerRepository, MainOfficeRepository mainOfficeRepository, UserRepository userRepository, FunctionRepository functionRepository) {
        this.postRepository = postRepository;
        this.clubRepository = clubRepository;
        this.areaRepository = areaRepository;
        this.centerRepository = centerRepository;
        this.mainOfficeRepository = mainOfficeRepository;
        this.userRepository = userRepository;
        this.functionRepository = functionRepository;
    }

    //CREATE A POST IN THE CLUB
    @Override
    public ResponseEntity<Object> createPost(PostRequest postRequest, Long idClub, Long idArea, Long idCenter, Long idMainOffice) {
        try {

            // CHECK NAME
            Utils.checkStringValues(postRequest.getName(), "Post name");

            List<Post> posts = new ArrayList<>();
            // NEW POST
            if(postRequest.getOrganisationLevelEnum().equals(OrganisationLevelEnum.MAINOFFICE)){
                if (idMainOffice == null || idMainOffice.equals(0L)) {
                    List<MainOffice> mainOffices = this.mainOfficeRepository.findAll();
                    mainOffices.forEach((mainOffice)->{
                        Post post = new Post();
                        post.setName(postRequest.getName());

                        if(postRequest.getMaxNumberOfUser() != null)
                            post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                        else
                            post.setMaxNumberOfUser(0);
                        post.setDescription(postRequest.getDescription());
                        post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                        post.setMainOffice(mainOffice);
                        posts.add(postRepository.save(post));
                    });
                }else{
                    Optional<MainOffice> mainOffice = this.mainOfficeRepository.findById(idMainOffice);
                    if(!mainOffice.isPresent()){
                        throw new Exception("MainOffice not found !");
                    }

                    Post post = new Post();
                    post.setName(postRequest.getName());

                    if(postRequest.getMaxNumberOfUser() != null)
                        post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                    else
                        post.setMaxNumberOfUser(0);
                    post.setDescription(postRequest.getDescription());
                    post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                    post.setMainOffice(mainOffice.get());
                    posts.add(postRepository.save(post));
                }

            }

            if(postRequest.getOrganisationLevelEnum().equals(OrganisationLevelEnum.CENTER)){

                if (idCenter == null || idCenter.equals(0L)){
                    List<Center> centers = this.centerRepository.findAll();
                    centers.forEach((center)->{
                        Post post = new Post();
                        post.setName(postRequest.getName());

                        if(postRequest.getMaxNumberOfUser() != null)
                            post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                        else
                            post.setMaxNumberOfUser(0);
                        post.setDescription(postRequest.getDescription());
                        post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                        post.setCenter(center);
                        posts.add(postRepository.save(post));
                    });
                }else{
                    Optional<Center> center = this.centerRepository.findById(idCenter);
                    if(!center.isPresent()){
                        throw new Exception("Center not found !");
                    }

                    Post post = new Post();
                    post.setName(postRequest.getName());

                    if(postRequest.getMaxNumberOfUser() != null)
                        post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                    else
                        post.setMaxNumberOfUser(0);
                    post.setDescription(postRequest.getDescription());
                    post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                    post.setCenter(center.get());
                    posts.add(postRepository.save(post));

                }


            }

            if(postRequest.getOrganisationLevelEnum().equals(OrganisationLevelEnum.AREA)){
                if (idArea == null || idArea.equals(0L)){
                    List<Area> areas = this.areaRepository.findAll();
                    areas.forEach((area)->{
                        Post post = new Post();
                        post.setName(postRequest.getName());

                        if(postRequest.getMaxNumberOfUser() != null)
                            post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                        else
                            post.setMaxNumberOfUser(0);
                        post.setDescription(postRequest.getDescription());
                        post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                        post.setArea(area);
                        posts.add(postRepository.save(post));
                    });
                }else{
                    Optional<Area> area = this.areaRepository.findById(idArea);
                    if(!area.isPresent()){
                        throw new Exception("Area not found !");
                    }

                    Post post = new Post();
                    post.setName(postRequest.getName());

                    if(postRequest.getMaxNumberOfUser() != null)
                        post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                    else
                        post.setMaxNumberOfUser(0);
                    post.setDescription(postRequest.getDescription());
                    post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                    post.setArea(area.get());
                    posts.add(postRepository.save(post));
                }


            }

            if(postRequest.getOrganisationLevelEnum().equals(OrganisationLevelEnum.CLUB)){
                if (idArea == null || idArea.equals(0L)){
                    List<Club> clubs = this.clubRepository.findAll();
                    clubs.forEach((club)->{
                        Post post = new Post();
                        post.setName(postRequest.getName());

                        if(postRequest.getMaxNumberOfUser() != null)
                            post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                        else
                            post.setMaxNumberOfUser(0);
                        post.setDescription(postRequest.getDescription());
                        post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                        post.setClub(club);
                        posts.add(postRepository.save(post));
                    });
                }else{
                    Optional<Club> club = this.clubRepository.findById(idClub);
                    if(!club.isPresent()){
                        throw new Exception("Club not found !");
                    }

                    Post post = new Post();
                    post.setName(postRequest.getName());

                    if(postRequest.getMaxNumberOfUser() != null)
                        post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                    else
                        post.setMaxNumberOfUser(0);
                    post.setDescription(postRequest.getDescription());
                    post.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                    post.setClub(club.get());
                    posts.add(postRepository.save(post));
                }

            }
            // SAVE POST
            return ResponseHandler.generateCreatedResponse("Posts list created !", posts);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public void createPostForLenelOrganisation(String name, Integer maxNumberOfUser, String description, OrganisationLevelEnum organisationLevelEnum, Long id) {
        Post post = new Post();
        post.setName(name);
        post.setMaxNumberOfUser(maxNumberOfUser);
        post.setDescription(description);
        post.setOrganisationLevelEnum(organisationLevelEnum);
        if(organisationLevelEnum.equals(OrganisationLevelEnum.MAINOFFICE)){
           Optional<MainOffice> mainOffice = mainOfficeRepository.findById(id);
            mainOffice.ifPresent(post::setMainOffice);
            this.postRepository.save(post);
        }else if(organisationLevelEnum.equals(OrganisationLevelEnum.CENTER)){
            Optional<Center> center = centerRepository.findById(id);
            center.ifPresent(post::setCenter);
            this.postRepository.save(post);
        }else if(organisationLevelEnum.equals(OrganisationLevelEnum.AREA)){
            Optional<Area> area = areaRepository.findById(id);
            area.ifPresent(post::setArea);
            this.postRepository.save(post);
        }else if(organisationLevelEnum.equals(OrganisationLevelEnum.CLUB)){
            Optional<Club> club = clubRepository.findById(id);
            club.ifPresent(post::setClub);
            this.postRepository.save(post);
        }


    }

    @Override
    public void createPostForMainOffice(String name, Integer maxNumberOfUser, String description, OrganisationLevelEnum organisationLevelEnum, MainOffice mainOffice) {
        Post post = new Post();
        post.setName(name);
        post.setMaxNumberOfUser(maxNumberOfUser);
        post.setDescription(description);
        post.setOrganisationLevelEnum(organisationLevelEnum);
        post.setMainOffice(mainOffice);
        this.postRepository.save(post);
    }

/*    //CREATE A POST IN THE AREA
    @Override
    public ResponseEntity<Object> createPostInArea(PostRequest postRequest) {
        try {

            //FIND A AREA BY ID
            Optional<Area> area = this.areaRepository.findById(idArea);

            if(!area.isPresent()){
                throw new Exception("Area not found !");
            }else{
                // CHECK NAME
                Utils.checkStringValues(postRequest.getName(), "Post name");

                // NEW POST
                Post post = new Post();
                post.setName(postRequest.getName());
                if(postRequest.getMaxNumberOfUser() != 0 && postRequest.getMaxNumberOfUser() != null)
                    post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                post.setDescription(postRequest.getDescription());
                post.setArea(area.get());
                // SAVE POST
                return ResponseHandler.generateCreatedResponse("Post created !", postRepository.save(post));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    //CREATE A POST IN THE CENTER
    @Override
    public ResponseEntity<Object> createPostInCenter(PostRequest postRequest) {
        try {

            //FIND A CENTER BY ID
            Optional<Center> center = this.centerRepository.findById(idCentre);

            if(!center.isPresent()){
                throw new Exception("Center not found !");
            }else{
                // CHECK NAME
                Utils.checkStringValues(postRequest.getName(), "Post name");

                // NEW POST
                Post post = new Post();
                post.setName(postRequest.getName());
                if(postRequest.getMaxNumberOfUser() != 0 && postRequest.getMaxNumberOfUser() != null)
                    post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                post.setDescription(postRequest.getDescription());
                post.setCenter(center.get());
                // SAVE POST
                return ResponseHandler.generateCreatedResponse("Post created !", postRepository.save(post));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    //CREATE A POST IN THE MAIN OFFICE
    @Override
    public ResponseEntity<Object> createPostInMainOffice(PostRequest postRequest) {
        try {

            //FIND A CENTER BY ID
            Optional<MainOffice> mainOffice = this.mainOfficeRepository.findById(idMainOffice);

            if(!mainOffice.isPresent()){
                throw new Exception("Main office not found !");
            }else{
                // CHECK NAME
                Utils.checkStringValues(postRequest.getName(), "Post name");

                // NEW POST
                Post post = new Post();
                post.setName(postRequest.getName());
                if(postRequest.getMaxNumberOfUser() != 0 && postRequest.getMaxNumberOfUser() != null)
                    post.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                post.setDescription(postRequest.getDescription());
                post.setMainOffice(mainOffice.get());
                // SAVE POST
                return ResponseHandler.generateCreatedResponse("Post created !", postRepository.save(post));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }*/

    // FIND ALL
    @Override
    public ResponseEntity<Object> findAllPosts() {

        List<Post> postList = postRepository.findAll();

        try {

            if(postList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }

            return ResponseHandler.generateOkResponse("Posts list", postList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findAllPostsByOrganisationLevel(OrganisationLevelEnum organisationLevelEnum) {
        List<Post> postList = postRepository.finAllPostByOrganisationLevel(organisationLevelEnum);

        try {

            if(postList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }

            return ResponseHandler.generateOkResponse("Posts list of " + organisationLevelEnum, postList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE POST
    @Override
    public ResponseEntity<Object> updatePost(Long idPost, PostRequest postRequest) {

        System.out.println("====================================postRequest:: "+ postRequest + "=============");
        // GET POST
        Optional<Post> post = postRepository.findById(idPost);

        try {

            return post.map(p -> {

                if(postRequest.getName() != null) {
                    p.setName(postRequest.getName() );
                }
                if(postRequest.getMaxNumberOfUser() > 0 && postRequest.getMaxNumberOfUser() != null){
                    p.setMaxNumberOfUser(postRequest.getMaxNumberOfUser());
                }else{
                    p.setMaxNumberOfUser(0);
                }
                if(postRequest.getOrganisationLevelEnum() != null){
                    p.setOrganisationLevelEnum(postRequest.getOrganisationLevelEnum());
                }
                p.setDescription(postRequest.getDescription());

                return ResponseHandler.generateOkResponse("Post " + idPost + " has properly been updated !",
                        postRepository.save(p));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Post not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> finAllPostByIdMainOffice(Long idPost) {
        List<Post> postList = postRepository.finAllPostByIdMainOffice(idPost);

        try {

            if(postList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }

            return ResponseHandler.generateOkResponse("Posts list of " + idPost, postList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> finAllPostByIdCenter(Long idCenter) {
        List<Post> postList = postRepository.finAllPostByIdCenter(idCenter);

        try {

            if(postList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }

            return ResponseHandler.generateOkResponse("Posts list of " + idCenter, postList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> finAllPostByIdArea(Long idArea) {
        List<Post> postList = postRepository.finAllPostByIdArea(idArea);

        try {

            if(postList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }

            return ResponseHandler.generateOkResponse("Posts list of " + idArea, postList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> finAllPostByIdClub(Long idClub) {
        List<Post> postList = postRepository.finAllPostByIdClub(idClub);

        try {

            if(postList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Empty list !");
            }

            return ResponseHandler.generateOkResponse("Posts list of " + idClub, postList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> addOperatorToPost(Long idOperator, Long idPost, Long idFunction) {
        Optional<User> operator = this.userRepository.findById(idOperator);
        AtomicBoolean isOperator = new AtomicBoolean(false);
        Optional<Post> post = this.postRepository.findById(idPost);
        Optional<Function> function = this.functionRepository.findById(idFunction);
        Post postToReturn = new Post();
        if(operator.isPresent()){
            try {
                operator.get().getRoles().forEach((role) -> {
                    if(role.getName().equals("OPERATOR")){
                        isOperator.set(true);
                    }
                });
                if(isOperator.get()){
                    function.ifPresent(value -> value.getOperators().add(operator.get()));
                    if(post.isPresent()) {
                        Post p = post.get();
                        if (!p.getOperators().contains(operator.get())) {
                            if(p.getMaxNumberOfUser() == 0){
                                p.getOperators().add(operator.get());
                            }else if(p.getMaxNumberOfUser() > p.getOperators().size()){
                                p.getOperators().add(operator.get());
                            }else{
                                return ResponseHandler.generateResponse("Le nombre maximal d'utilisateur que peut contenir ce poste a déjà été atteint !", HttpStatus.BAD_REQUEST, null);
                            }
                        }
                        functionRepository.save(function.get());
                        postToReturn =  postRepository.save(p);
                    }

                }
                // SAVE
                return ResponseHandler.generateResponse("The operator has been properly added !", HttpStatus.CREATED, postToReturn);
            } catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }else {
            return ResponseHandler.generateResponse("Operator not found !", HttpStatus.BAD_REQUEST, null);
        }

    }

    //REMOVE AN OPERATOR FROM A POST
    @Override
    public ResponseEntity<Object> removeAnOperatorFromAPost(Long idPost, Long idOperator) {

        // GET POST
        Optional<Post> post = this.postRepository.findById(idPost);

        // GET OPERATOR
        Optional<User> operator = this.userRepository.findById(idOperator);

        try {

            if(!post.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Post not found !");
            }

            if(!operator.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Operator not found !");
            }
            AtomicBoolean isAnOperator = new AtomicBoolean(false);
            operator.get().getRoles().forEach(role -> {
                if(role.getName().equals("OPERATOR")){
                    isAnOperator.set(true);
                }
            });

            if(!isAnOperator.get()){
                return ResponseHandler.generateNotFoundResponse("The user is not an operator !");
            }

            post.get().getOperators().remove(operator.get());
            return ResponseHandler.generateResponse("Operator has properly been removed !",
                    HttpStatus.OK, postRepository.save(post.get()));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findPostByName(String name) {
        // GET ALL CLUBS
        try {
            List<Post> posts = postRepository.findPostByName(name);
            if (posts.isEmpty()) {
                return ResponseHandler.generateResponse("Posts list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("Posts list", HttpStatus.OK, posts);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    @Override
    public ResponseEntity<Object> createApostForAclub(PostRequest postRequest, Long idClub) {
        return null;
    }

    @Override
    public ResponseEntity<Object> createApostForAarea(PostRequest postRequest, Long idArea) {
        return null;
    }

    @Override
    public ResponseEntity<Object> createApostForACenter(PostRequest postRequest, Long idCenter) {
        return null;
    }

    @Override
    public ResponseEntity<Object> createApostForAmainOffcice(PostRequest postRequest, Long idMainOffice) {
        return null;
    }


    // DELETE POST
    @Override
    public ResponseEntity<Object> deletePost(Long idPost) {

        // GET POST
        Optional<Post> post = postRepository.findById(idPost);

        try {

            if(!post.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Post not found !");
            }

            postRepository.deleteById(idPost);
            return ResponseHandler.generateOkResponse("Post " + idPost + " has properly been deleted !", null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND POST
    @Override
    public ResponseEntity<Object> findPostById(Long idPost) {

        // GET POST
        Optional<Post> post = postRepository.findById(idPost);

        try {

            return post.map(p -> ResponseHandler.generateOkResponse("Post " + idPost, p))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Post not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL
    @Override
    public Long countAll() {
        return postRepository.count();
    }


    // SAVE POST
    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
