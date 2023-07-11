package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.dto.request.CenterRequest;
import com.b2i.serviceorganisation.dto.request.UserRequest;
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
public class CenterServiceImplementation implements CenterService {

    private final CenterRepository centerRepository;

    private final UserRepository userRepository;

    private final AreaRepository areaRepository;

    private final MainOfficeRepository mainOfficeRepository;

    private final PostRepository postRepository;

    private final PostServiceImplementation postServiceImplementation;

    private final FunctionRepository functionRepository;

    private final ClubRepository clubRepository;


    public CenterServiceImplementation(CenterRepository centerRepository, UserRepository userRepository, AreaRepository areaRepository, MainOfficeRepository mainOfficeRepository, PostRepository postRepository, PostServiceImplementation postServiceImplementation, FunctionRepository functionRepository, ClubRepository clubRepository) {
        this.centerRepository = centerRepository;
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.mainOfficeRepository = mainOfficeRepository;
        this.postRepository = postRepository;
        this.postServiceImplementation = postServiceImplementation;
        this.functionRepository = functionRepository;
        this.clubRepository = clubRepository;
    }

    // CREATE CENTER
    @Override
    public ResponseEntity<Object> createCenter(CenterRequest centerRequest) {

        // CREATE CENTER
        Center center = new Center();
        //FIND ALL LIST OF POSTS IN CENTER
        try {

            // CHECK NAME
            Utils.checkStringValues(centerRequest.getName(), "Center name");

            // CHECK REFERENCE
            Utils.checkStringValues(centerRequest.getReference(), "Center reference");

            // CHECK OBSERVATION
            Utils.checkStringValues(centerRequest.getObservation(), "Center observation");

            // SETTING VALUE
            center.setName(centerRequest.getName());
            center.setReference(centerRequest.getReference());
            center.setObservation(centerRequest.getObservation());
            Center c = centerRepository.save(center);

            //CREATING DEFAULTS POSTS OF THE CENTER
            postServiceImplementation.createPostForLenelOrganisation("Comptable", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());
            postServiceImplementation.createPostForLenelOrganisation("Administrateur Système", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());
            postServiceImplementation.createPostForLenelOrganisation("Assemblée générale des membres", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());
            postServiceImplementation.createPostForLenelOrganisation("Comité de Développement", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());
            postServiceImplementation.createPostForLenelOrganisation("Assemblée représentative des Clubs", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());
            postServiceImplementation.createPostForLenelOrganisation("Conseil d’Administration", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());
            postServiceImplementation.createPostForLenelOrganisation("Comité de Gouvernance et des Rémunérations", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());
            postServiceImplementation.createPostForLenelOrganisation("Responsable de Production", 1, "description du poste", OrganisationLevelEnum.CENTER, c.getId());

            // ADD CENTER TO MAIN OFFICE
            Optional<MainOffice> office = mainOfficeRepository.findById(1L);

            if (office.isPresent()) {
                office.get().getCenters().add(c);
                mainOfficeRepository.save(office.get());
            }

            return ResponseHandler.generateResponse("This center has been properly created !", HttpStatus.CREATED, c);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL
    @Override
    public ResponseEntity<Object> findAllCenters() {

        // GET ALL CENTERS
        try {
            List<Center> centers = centerRepository.findAll();

            if (centers.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Centers list");
            }
            return ResponseHandler.generateResponse("Centers list", HttpStatus.OK, centers);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE CENTER
    @Override
    public ResponseEntity<Object> updateCenter(Long id, CenterRequest centerRequest) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(id);
        Center c = new Center();

        try {
            // IF CENTER IS NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }else{
                c = center.get();

                if (centerRequest.getName() != null) {
                    c.setName(centerRequest.getName());
                } else {
                    return ResponseHandler.generateResponse("The center name can't be null !", HttpStatus.BAD_REQUEST, null);
                }

                if (centerRequest.getReference() != null) {
                    c.setReference(centerRequest.getReference());
                } else {
                    return ResponseHandler.generateResponse("The center reference can't be null !", HttpStatus.BAD_REQUEST, null);
                }

                if (centerRequest.getObservation() != null) {
                    c.setObservation(centerRequest.getObservation());
                } else {
                    return ResponseHandler.generateResponse("The center reference can't be null !", HttpStatus.BAD_REQUEST, null);
                }
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("The center " + id + " has properly been updated !", HttpStatus.OK, c);
            }

        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE CENTER
    @Override
    public ResponseEntity<Object> deleteCenter(Long id) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(id);

        try {
            // IF CENTER IS NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            centerRepository.deleteById(id);
            return ResponseHandler.generateResponse("The center " + id + " has been properly deleted !",
                    HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND CENTER BY ID
    @Override
    public ResponseEntity<Object> findById(Long id) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(id);
        Center c = new Center();
        try {
            // IF CENTER IS NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            c = center.get();
            return ResponseHandler.generateResponse("Center", HttpStatus.OK, c);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> addPost(Long idCenter, Long idPost) {
        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);
        Optional<Post> post = postRepository.findById(idPost);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // POST NOT PRESENT
            else if (!post.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Post not found");
            } else {
                Center c = center.get();
                if (!c.getPosts().contains(post.get())) {
                    c.getPosts().add(post.get());
                }
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("The post " + idPost + " has properly been " +
                        "added to center " + idCenter + " !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findCenterByIdArea(Long idArea) {
        return ResponseHandler.generateResponse("found center : ", HttpStatus.OK, this.centerRepository.findCenterByIdArea(idArea));
    }

    @Override
    public ResponseEntity<Object> findCenterByIdUser(Long idUser) {
        //GET CLUB BY ID USER
        Club club = this.clubRepository.findClubByIdUser(idUser);
        if (club == null)
            return ResponseHandler.generateNotFoundResponse("club not found");

        //GET AREA BY ID CLUB
        Area area = this.areaRepository.findAreaByIdClub(club.getId());
        if (area == null)
            return ResponseHandler.generateNotFoundResponse("area not found");

        //GET CENTER BY ID AREA
        Center center = this.centerRepository.findCenterByIdArea(area.getId());
        if(center == null)
            return ResponseHandler.generateNotFoundResponse("center not found");
        return ResponseHandler.generateResponse("Get center ", HttpStatus.OK, center);
    }

    @Override
    public ResponseEntity<Object> findUsersByIdCenter(Long idCenter) {
        //GET CENTER BY ID
        Optional<Center> center = this.centerRepository.findById(idCenter);
        try {
            if(!center.isPresent())
                return ResponseHandler.generateNotFoundResponse("center not found");
            List<User> users = new ArrayList<>();
            center.get().getAreas().forEach((area)->{
                area.getClubs().forEach((club)->{
                    users.addAll(club.getMembers());
                });
            });
            return ResponseHandler.generateResponse("All users of center", HttpStatus.OK, users);
        }catch (Exception e){
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findMutualistsByIdCenter(Long idCenter) {

        Optional<Center> center = this.centerRepository.findById(idCenter);
        try {
            if(!center.isPresent())
                return ResponseHandler.generateNotFoundResponse("center not found");
            List<User> users = new ArrayList<>();
            center.get().getAreas().forEach((area)->{
                area.getClubs().forEach((club)->{
                    club.getMembers().forEach(member -> {
                        AtomicBoolean isMutualist = new AtomicBoolean(false);
                        member.getRoles().forEach(r -> {
                            if(r.getName().equals("MUTUALIST")){
                                isMutualist.set(true);
                            }
                        });
                        if(isMutualist.get()){
                            users.add(member);
                        }
                    });
                });
            });
            return ResponseHandler.generateResponse("All users of center", HttpStatus.OK, users);
        }catch (Exception e){
            return ResponseHandler.generateError(e);
        }
    }


/*    // ADD MEMBER AS ADMIN SYS
    @Override
    public ResponseEntity<Object> addAdminSys(Long idCenter, Long idMember) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);
        Optional<User> member = userRepository.findById(idMember);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // MEMBER NOT PRESENT
            else if (!member.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found");
            } else {
                Center c = center.get();
                c.setAdminSys(member.get());
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("The member " + idMember + " has properly been " +
                        "added as system administrator for center " + idCenter + " !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // REMOVE MEMBER AS ADMIN SYS
    @Override
    public ResponseEntity<Object> removeAdminSys(Long idCenter) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            } else {
                Center c = center.get();
                if (!Objects.isNull(c.getAdminSys())) {
                    c.setAdminSys(null);
                }
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("Center " + idCenter + " system administrator has been " +
                        "properly removed !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // ADD MEMBER AS ACCOUNTANT
    @Override
    public ResponseEntity<Object> addAccountant(Long idCenter, Long idMember) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);
        Optional<User> member = userRepository.findById(idMember);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // MEMBER NOT PRESENT
            else if (!member.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found");
            } else {
                Center c = center.get();
                c.setAccountant(member.get());
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("The member " + idMember + " has properly been " +
                        "added as accountant for center " + idCenter + " !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }*/


/*
    // REMOVE MEMBER AS ACCOUNTANT
    @Override
    public ResponseEntity<Object> removeAccountant(Long idCenter) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            } else {
                Center c = center.get();
                if (!Objects.isNull(c.getAccountant())) {
                    c.setAccountant(null);
                }
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("Center " + idCenter + " accountant has been " +
                        "properly removed !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

*/

/*
    // ADD MEMBER AS PRODUCTION MANAGER
    @Override
    public ResponseEntity<Object> addProductionManager(Long idCenter, Long idMember) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);
        Optional<User> member = userRepository.findById(idMember);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // MEMBER NOT PRESENT
            else if (!member.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found");
            } else {
                Center c = center.get();
                c.setProductionManager(member.get());
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("The member " + idMember + " has properly been " +
                        "added as production manager for center " + idCenter + " !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // REMOVE MEMBER AS PRODUCTION MANAGER
    @Override
    public ResponseEntity<Object> removeProductionManager(Long idCenter) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            } else {
                Center c = center.get();
                if (!Objects.isNull(c.getProductionManager())) {
                    c.setProductionManager(null);
                }
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("Center " + idCenter + " production manager has been " +
                        "properly removed !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }
*/


/*    // ADD MEMBER TO MEMBERS GENERAL ASSEMBLY
    @Override
    public ResponseEntity<Object> addMemberToMembersGeneralAssembly(Long idCenter, Long idMember, Long idPost) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return addMemberToBureau(center.get(), idMember, center.get().getMembersGeneralAssembly(), idPost);
    }


    // REMOVE MEMBER FROM MEMBERS GENERAL ASSEMBLY
    @Override
    public ResponseEntity<Object> removeMemberFromMembersGeneralAssembly(Long idCenter, Long idMember) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return removeFromBureau(center.get(), idMember, center.get().getMembersGeneralAssembly());
    }


    // ADD MEMBER TO CLUBS GENERAL ASSEMBLY
    @Override
    public ResponseEntity<Object> addMemberToClubsGeneralAssembly(Long idCenter, Long idMember, Long idPost) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return addMemberToBureau(center.get(), idMember, center.get().getClubsGeneralAssembly(), idPost);
    }


    // REMOVE MEMBER FROM CLUBS GENERAL ASSEMBLY
    @Override
    public ResponseEntity<Object> removeMemberFromClubsGeneralAssembly(Long idCenter, Long idMember) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return removeFromBureau(center.get(), idMember, center.get().getClubsGeneralAssembly());
    }*/


    // ADD AREA
    @Override
    public ResponseEntity<Object> addArea(Long idCenter, Long idArea) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);
        Optional<Area> area = areaRepository.findById(idArea);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // MEMBER NOT PRESENT
            else if (!area.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Area not found");
            } else {
                Center c = center.get();
                if (!c.getAreas().contains(area.get())) {
                    c.getAreas().add(area.get());
                }
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("The area " + idArea + " has properly been " +
                        "added to areas of center " + idCenter + " !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // REMOVE AREA
    @Override
    public ResponseEntity<Object> removeArea(Long idCenter, Long idArea) {

        // GET CENTER & MEMBER
        Optional<Center> center = centerRepository.findById(idCenter);
        Optional<Area> area = areaRepository.findById(idArea);

        // TESTS
        try {
            // CENTER NOT PRESENT
            if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // MEMBER NOT PRESENT
            else if (!area.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found");
            } else {
                Center c = center.get();
                c.getAreas().remove(area.get());
                c = centerRepository.save(c);
                return ResponseHandler.generateResponse("The member " + idArea + " has properly been " +
                        "removed from areas of center " + idCenter + " !", HttpStatus.OK, c);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


/*
    // ADD MEMBER TO EXECUTIVE BOARD
    @Override
    public ResponseEntity<Object> addMemberToExecutiveBoard(Long idCenter, Long idMember, Long idPost) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return addMemberToBureau(center.get(), idMember, center.get().getExecutiveBoard(), idPost);
    }
*/


/*
    // REMOVE FROM EXECUTIVE BOARD
    @Override
    public ResponseEntity<Object> removeMemberFromExecutiveBoard(Long idCenter, Long idMember) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return removeFromBureau(center.get(), idMember, center.get().getExecutiveBoard());
    }
*/


/*
    // ADD TO DEVELOPMENT COMMITTEE
    @Override
    public ResponseEntity<Object> addMemberToDevelopmentCommittee(Long idCenter, Long idMember, Long idPost) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return addMemberToBureau(center.get(), idMember, center.get().getDevelopmentCommittee(), idPost);
    }
*/


/*
    // REMOVE FROM DEVELOPMENT COMMITTEE
    @Override
    public ResponseEntity<Object> removeMemberFromDevelopmentCommittee(Long idCenter, Long idMember) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return removeFromBureau(center.get(), idMember, center.get().getDevelopmentCommittee());
    }
*/


/*
    // ADD TO GCC
    @Override
    public ResponseEntity<Object> addMemberToGovernanceAndCompensationCommittee(Long idCenter, Long idMember, Long idPost) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return addMemberToBureau(center.get(), idMember, center.get().getGovernanceAndCompensationCommittee(), idPost);
    }
*/


/*
    // REMOVE FROM GCC
    @Override
    public ResponseEntity<Object> removeMemberFromGovernanceAndCompensationCommittee(Long idCenter, Long idMember) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        // CENTER NOT PRESENT
        if (!center.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Center not found");
        }

        return removeFromBureau(center.get(), idMember, center.get().getGovernanceAndCompensationCommittee());
    }
*/


    // GET ALL CENTER USERS
    @Override
    public ResponseEntity<Object> getAllCenterUsers(Long idCenter) {

        // GET CENTER
        Optional<Center> center = centerRepository.findById(idCenter);

        try {

            return center.map(c -> {

                // GET ALL AREAS
                List<Area> areas = new ArrayList<>(c.getAreas());

                // GET ALL CLUBS
                List<Club> clubs = new ArrayList<>();
                areas.forEach(a -> {
                    clubs.addAll(a.getClubs());
                });

                // GET ALL USERS
                List<User> users = new ArrayList<>();
                clubs.forEach(club -> {
                    users.addAll(club.getMembers());
                });

                List<UserRequest> userRequests = new ArrayList<>();
                List<Long> usersId = new ArrayList<>();
                users.forEach(u -> {
                    UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    userRequests.add(ur);
                    usersId.add(u.getId());
                });

                // return ResponseHandler.generateResponse("Center " + idCenter + " members", HttpStatus.OK, userRequests);
                return ResponseHandler.generateResponse("Center " + idCenter + " members", HttpStatus.OK, usersId);
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Center not found !"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findCentersByName(String name) {
        // GET ALL CENTERS
        try {
            List<Center> centers = centerRepository.findCenterByName(name);

            if (centers.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Centers list");
            }
            return ResponseHandler.generateResponse("Centers list", HttpStatus.OK, centers);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }




    /* **************************************** OTHERS  **************************************** */


  /*  // ADD TO BUREAU
    private ResponseEntity<Object> addMemberToBureau(Center center, Long idMember, List<User> bureau, Long idPost) {

        // GET MEMBER
        Optional<User> member = userRepository.findById(idMember);

        // GET POST
        Optional<Post> post = postRepository.findById(idPost);

        // TESTS
        try {

            // MEMBER NOT PRESENT
            if (!member.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found");
            }

            if (!post.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Post not found");
            }

            User u = member.get();
            u.setPost(post.get());
            u = userRepository.save(u);

            if (!bureau.contains(u)) {
                bureau.add(u);
            }

            // u.setInCenterGovernanceAndCompensationCommittee(true);
            // userRepository.save(u);

            return ResponseHandler.generateOkResponse("Member properly added !", centerRepository.save(center));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // REMOVE FROM BUREAU
    private ResponseEntity<Object> removeFromBureau(Center center, Long idMember, List<User> bureau) {

        // GET MEMBER
        Optional<User> member = userRepository.findById(idMember);

        // TESTS
        try {

            // MEMBER NOT PRESENT
            if (!member.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found");
            }


            User u = member.get();
            u.setPost(null);
            // u.setInCenterGovernanceAndCompensationCommittee(false);
            userRepository.save(u);

            bureau.remove(u);
            return ResponseHandler.generateOkResponse("Member has properly been removed from bureau", centerRepository.save(center));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }*/
}
