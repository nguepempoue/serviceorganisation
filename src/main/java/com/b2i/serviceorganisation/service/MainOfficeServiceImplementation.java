package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.dto.request.MainOfficeRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.*;
import com.b2i.serviceorganisation.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainOfficeServiceImplementation implements MainOfficeService {

    private final MainOfficeRepository mainOfficeRepository;

    private final CenterRepository centerRepository;

    private final UserRepository userRepository;

    private final FunctionRepository functionRepository;

    private final PostRepository postRepository;

    private final PostServiceImplementation postServiceImplementation;

    public MainOfficeServiceImplementation(MainOfficeRepository mainOfficeRepository, CenterRepository centerRepository, UserRepository userRepository, FunctionRepository functionRepository, PostRepository postRepository, PostServiceImplementation postServiceImplementation) {
        this.mainOfficeRepository = mainOfficeRepository;
        this.centerRepository = centerRepository;
        this.userRepository = userRepository;
        this.functionRepository = functionRepository;
        this.postRepository = postRepository;
        this.postServiceImplementation = postServiceImplementation;
    }


    // CREATE MAIN OFFICE
    @Override
    public ResponseEntity<Object> createMainOffice(MainOfficeRequest mainOfficeRequest) {

        if (mainOfficeRepository.count() != 0) {
            return ResponseHandler.generateResponse("There's already a main office created !", HttpStatus.MULTI_STATUS, null);
        }

        // CREATE OFFICE
        MainOffice office = new MainOffice();
        List<Center> centers = centerRepository.findAll();
        // SETTING VALUES
        try {
            if (!centers.isEmpty()) {
                office.setCenters(centers);
            }

            if (mainOfficeRequest.getName() != null) {
                office.setName(mainOfficeRequest.getName());
            } else {
                return ResponseHandler.generateResponse("Main office name can't be null !", HttpStatus.BAD_REQUEST, null);
            }
            // SAVE
            office = mainOfficeRepository.save(office);

            System.out.println("============================== office::" + office);
            postServiceImplementation.createPostForLenelOrganisation("Comité exécutif", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE, office.getId());
            postServiceImplementation.createPostForLenelOrganisation("Assemblée générale", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE,  office.getId());
            postServiceImplementation.createPostForLenelOrganisation("Comité de gouvernance et de rémunération", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE,  office.getId());
            postServiceImplementation.createPostForLenelOrganisation("Comité de production et de surveillance", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE,  office.getId());
            postServiceImplementation.createPostForLenelOrganisation("Comité de développement stratégique", 1, "description du poste", OrganisationLevelEnum.MAINOFFICE,  office.getId());

            return ResponseHandler.generateResponse("The main office has been properly created !", HttpStatus.CREATED, office);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL MAIN OFFICES
    @Override
    public ResponseEntity<Object> findAllMainOffices() {

        // GET ALL MAIN OFFICES
        try {
            List<MainOffice> offices = mainOfficeRepository.findAll();

            if (offices.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Main offices list");
            } else {
                return ResponseHandler.generateResponse("Main offices list", HttpStatus.OK, offices);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE MAIN OFFICE
    @Override
    public ResponseEntity<Object> updateMainOfficeRequest(Long id, MainOfficeRequest mainOfficeRequest) {

        // GET MAIN OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(id);
        MainOffice o = new MainOffice();
        try {
            // IF MAIN OFFICE EXISTS
            if (!office.isPresent()) {
                ResponseHandler.generateNotFoundResponse("Office not found !");
            } else {
                o = office.get();
                if (mainOfficeRequest.getName() != null) {
                    o.setName(mainOfficeRequest.getName());
                    o = mainOfficeRepository.save(o);
                } else {
                    return ResponseHandler.generateResponse("Main office name can't be null !", HttpStatus.BAD_REQUEST, null);
                }
            }
            return ResponseHandler.generateResponse("The office " + id + " has been properly updated !", HttpStatus.OK, o);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE MAIN OFFICE
    @Override
    public ResponseEntity<Object> deleteMainOffice(Long id) {

        // GET MAIN OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(id);

        // DELETING
        try {
            if (!office.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Office not found !");
            }
            mainOfficeRepository.deleteById(id);
            return ResponseHandler.generateResponse("The office " + id + " has been properly deleted !", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND BY ID
    @Override
    public ResponseEntity<Object> findMainOfficeById(Long id) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(id);

        // IF PRESENT
        try {
            if (!office.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Office not found !");
            }
            MainOffice o = office.get();
            return ResponseHandler.generateResponse("Office " + id, HttpStatus.OK, o);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // ADD CENTER
    @Override
    public ResponseEntity<Object> addCenter(Long idMainOffice, Long idCenter) {

        // GET OFFICE & CENTER
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);
        Optional<Center> center = centerRepository.findById(idCenter);

        try {
            if (!office.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Office not found !");
            } else if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found !");
            } else {
                MainOffice o = office.get();
                if (!o.getCenters().contains(center.get())) {
                    o.getCenters().add(center.get());
                }
                o = mainOfficeRepository.save(o);
                return ResponseHandler.generateResponse("The center " + idCenter + " has been " +
                        "properly added to office " + idMainOffice, HttpStatus.OK, o);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // REMOVE CENTER
    @Override
    public ResponseEntity<Object> removeCenter(Long idMainOffice, Long idCenter) {

        // GET OFFICE & CENTER
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);
        Optional<Center> center = centerRepository.findById(idCenter);

        try {
            if (!office.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Office not found !");
            } else if (!center.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found !");
            } else {
                MainOffice o = office.get();
                o.getCenters().remove(center.get());
                o = mainOfficeRepository.save(o);
                return ResponseHandler.generateResponse("The center " + idCenter + " has been " +
                        "properly removed from office " + idMainOffice, HttpStatus.OK, o);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    //ADD A OPERATOR TO POST IN THE MAIN OFFICE




/*    // ADD TO CENTERS GENERAL ASSEMBLY
    @Override
    public ResponseEntity<Object> addToCentersGeneralAssembly(Long idMainOffice, Long idMember, Long idPost) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return addToBureau(office.get(), idMember, office.get().getCentersGeneralAssembly(), idPost);
    }


    // REMOVE CENTERS GENERAL ASSEMBLY
    @Override
    public ResponseEntity<Object> removeFromCentersGeneralAssembly(Long idMainOffice, Long idMember) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return removeFromBureau(office.get(), idMember, office.get().getCentersGeneralAssembly());
    }


    // ADD TO EXECUTIVE BOARD
    @Override
    public ResponseEntity<Object> addToExecutiveBoard(Long idMainOffice, Long idMember, Long idPost) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return addToBureau(office.get(), idMember, office.get().getExecutiveBoard(), idPost);
    }


    // REMOVE FROM EXECUTIVE BOARD
    @Override
    public ResponseEntity<Object> removeFromExecutiveBoard(Long idMainOffice, Long idMember) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return removeFromBureau(office.get(), idMember, office.get().getExecutiveBoard());
    }


    // ADD TO GCC
    @Override
    public ResponseEntity<Object> addToGovernanceAndCompensationCommittee(Long idMainOffice, Long idMember, Long idPost) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return addToBureau(office.get(), idMember, office.get().getGovernanceAndCompensationCommittee(), idPost);
    }


    // REMOVE FROM GCC
    @Override
    public ResponseEntity<Object> removeFromGovernanceAndCompensationCommittee(Long idMainOffice, Long idMember) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return removeFromBureau(office.get(), idMember, office.get().getGovernanceAndCompensationCommittee());
    }


    // ADD TO SDC
    @Override
    public ResponseEntity<Object> addToStrategicDevelopmentCommittee(Long idMainOffice, Long idMember, Long idPost) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return addToBureau(office.get(), idMember, office.get().getStrategicDevelopmentCommittee(), idPost);
    }


    // REMOVE FROM SDC
    @Override
    public ResponseEntity<Object> removeFromStrategicDevelopmentCommittee(Long idMainOffice, Long idMember) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return removeFromBureau(office.get(), idMember, office.get().getStrategicDevelopmentCommittee());
    }


    // ADD TO PMC
    @Override
    public ResponseEntity<Object> addToProductionAndMonitoringCommittee(Long idMainOffice, Long idMember, Long idPost) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return addToBureau(office.get(), idMember, office.get().getProductionAndMonitoringCommittee(), idPost);
    }


    // REMOVE FROM PMC
    @Override
    public ResponseEntity<Object> removeFromProductionAndMonitoringCommittee(Long idMainOffice, Long idMember) {

        // GET OFFICE
        Optional<MainOffice> office = mainOfficeRepository.findById(idMainOffice);

        if (!office.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Office not found !");
        }

        return removeFromBureau(office.get(), idMember, office.get().getProductionAndMonitoringCommittee());
    }*/




    /* **************************************** OTHERS **************************************** */




    /*// ADD TO BUREAU
    private ResponseEntity<Object> addToBureau(MainOffice mainOffice, Long idMember, List<User> bureau, Long idPost) {

        // GET MEMBER
        Optional<User> member = userRepository.findById(idMember);

        // GET POST
        Optional<Post> post = postRepository.findById(idPost);

        try {

            if (!post.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Post not found");
            }

            if (!member.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found !");
            }

            User u = member.get();
            u.setPost(post.get());
            u = userRepository.save(u);

            if (!bureau.contains(u)) {
                bureau.add(u);
            }

            // u.setInMainOfficeProductionAndMonitoringCommittee(true);
            // userRepository.save(u);

            return ResponseHandler.generateOkResponse("Member properly added to bureau !", mainOfficeRepository.save(mainOffice));
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // REMOVE FROM BUREAU
    private ResponseEntity<Object> removeFromBureau(MainOffice mainOffice, Long idMember, List<User> bureau) {

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
            return ResponseHandler.generateOkResponse("Member has properly been removed from bureau", mainOfficeRepository.save(mainOffice));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }*/
}
