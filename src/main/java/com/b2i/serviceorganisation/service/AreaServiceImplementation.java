package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.dto.request.AreaRequest;
import com.b2i.serviceorganisation.dto.request.UserRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.*;
import com.b2i.serviceorganisation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AreaServiceImplementation implements AreaService {

    private final AreaRepository areaRepository;

    private final ClubRepository clubRepository;

    private final UserRepository userRepository;

    private final CenterRepository centerRepository;

    private final PostRepository postRepository;

    private final PostServiceImplementation postServiceImplementation;

    public AreaServiceImplementation(AreaRepository areaRepository, ClubRepository clubRepository, UserRepository userRepository, CenterRepository centerRepository, PostRepository postRepository, PostServiceImplementation postServiceImplementation) {
        this.areaRepository = areaRepository;
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.centerRepository = centerRepository;
        this.postRepository = postRepository;
        this.postServiceImplementation = postServiceImplementation;
    }

    @Autowired
    private CenterServiceImplementation centerServiceImplementation;


    // CREATE AREAS
    @Override
    public ResponseEntity<Object> createArea(AreaRequest areaRequest) {

        // CREATE AREA
        Area area = new Area();
        //FIND ALL LIST OF POSTS IN AREAS
        //List<Post> posts = postRepository.finAllPostByOrganisationLevel(OrganisationLevelEnum.AREA);
        try {

            // CHECK NAME
            Utils.checkStringValues(areaRequest.getName(), "Area name");

            // CHECK REFERENCE
            Utils.checkStringValues(areaRequest.getReference(), "Area reference");

            // CHECK OBSERVATION
            Utils.checkStringValues(areaRequest.getObservation(), "Area observation");

            // SETTING VALUES
            area.setName(areaRequest.getName());
            area.setReference(areaRequest.getReference());
            area.setObservation(areaRequest.getObservation());
            //area.setPosts(posts);
            Area a = areaRepository.save(area);

            postServiceImplementation.createPostForLenelOrganisation("Agent de communication", 1, "description du poste", OrganisationLevelEnum.AREA, a.getId());
            postServiceImplementation.createPostForLenelOrganisation("Agent de saisie de données", 1, "description du poste", OrganisationLevelEnum.AREA, a.getId());

            // ADD TO A CENTER
           // centerServiceImplementation.addArea(idCenter, a.getId());

            return ResponseHandler.generateResponse("This area has properly been added !", HttpStatus.CREATED, a);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL AREAS
    @Override
    public ResponseEntity<Object> findAllAreas() {

        // GET ALL CLUBS
        try {
            List<Area> areas = areaRepository.findAll();
            if (areas.isEmpty()) {
                return ResponseHandler.generateResponse("Clubs list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("Clubs list", HttpStatus.OK, areas);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE AREA
    @Override
    public ResponseEntity<Object> updateArea(Long id, AreaRequest areaRequest) {

        // GET AREA
        Optional<Area> area = areaRepository.findById(id);
        Area a = new Area();

        // SETTING NEW VALUES
        Club club = new Club();
        //FIND ALL LIST OF POSTS IN AREAS
        List<Post> posts = postRepository.finAllPostByOrganisationLevel(OrganisationLevelEnum.CLUB);
        try {
            if (!area.isPresent()) {
                ResponseHandler.generateResponse("Area not found", HttpStatus.NOT_FOUND, null);
            }
            else {
                a = area.get();
                if(areaRequest.getName() != null) {
                    a.setName(areaRequest.getName());
                }
                else {
                    return ResponseHandler.generateResponse("Area name can't be null !", HttpStatus.BAD_REQUEST, null);
                }

                if(areaRequest.getReference() != null) {
                    a.setReference(areaRequest.getReference());
                }
                else {
                    return ResponseHandler.generateResponse("Area reference can't be null !", HttpStatus.BAD_REQUEST, null);
                }

                if(areaRequest.getObservation() != null) {
                    a.setObservation(areaRequest.getObservation());
                }
                else {
                    return ResponseHandler.generateResponse("Area observation can't be null !", HttpStatus.BAD_REQUEST, null);
                }
                a = areaRepository.save(a);
            }
            return ResponseHandler.generateResponse("The area " + id + " has been properly updated !", HttpStatus.OK, a);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE AREA BY ID
    @Override
    public ResponseEntity<Object> deleteById(Long id) {

        // GET AREA
        Optional<Area> area = areaRepository.findById(id);

        if (!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        } else {
            try {
                areaRepository.deleteById(id);
                return ResponseHandler.generateResponse("The area " + id + " has been properly deleted !", HttpStatus.OK, null);
            } catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }


    // GET AREA BY ID
    @Override
    public ResponseEntity<Object> findAreaById(Long id) {

        // GET AREA
        Optional<Area> area = areaRepository.findById(id);
        try {
            if (!area.isPresent()) {
                return ResponseHandler.generateResponse("Area not found ", HttpStatus.NOT_FOUND, null);
            }
            return ResponseHandler.generateResponse("Area : " + id, HttpStatus.OK, area.get());
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL AREAS
    @Override
    public ResponseEntity<Object> countAllAreas() {
        Long areasNumber = areaRepository.count();
        return ResponseHandler.generateResponse("Areas number : ", HttpStatus.OK, areasNumber);
    }


    // ADD CLUB TO AN AREA
    @Override
    public ResponseEntity<Object> addClub(Long idArea, Long idClub) {

        // GET AREA & CLUB
        Optional<Area> area = areaRepository.findById(idArea);
        Optional<Club> club = clubRepository.findById(idClub);

        Area a = new Area();

        if(!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        }
        else if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found !", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                a = area.get();
                if(!a.getClubs().contains(club.get())){
                    a.getClubs().add(club.get());
                }
                a = areaRepository.save(a);
                return ResponseHandler.generateResponse("Club " + idClub + " has been properly added to Area "
                                + idArea + " !", HttpStatus.OK, a);
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }


    // REMOVE CLUB FROM AREA
    @Override
    public ResponseEntity<Object> removeClub(Long idArea, Long idClub) {
        // GET AREA & CLUB
        Optional<Area> area = areaRepository.findById(idArea);
        Optional<Club> club = clubRepository.findById(idClub);

        Area a = new Area();

        if(!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        }
        else if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found !", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                a = area.get();
                a.getClubs().remove(club.get());
                a = areaRepository.save(a);
                return ResponseHandler.generateResponse("Club " + idClub + " has been properly removed from Area "
                        + idArea + " !", HttpStatus.OK, a);
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }

    @Override
    public ResponseEntity<Object> findAreaByIdClub(Long idClub) {
        return ResponseHandler.generateResponse("found area : ", HttpStatus.OK, this.areaRepository.findAreaByIdClub(idClub));
    }


/*
    // ADD DATA ENTRY AGENT
    @Override
    public ResponseEntity<Object> addDataEntryAgent(Long idArea, Long idAgent) {

        // GET AREA & AGENT
        Optional<Area> area = areaRepository.findById(idArea);
        Optional<User> agent = userRepository.findById(idAgent);

        Area a = new Area();

        if(!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        }
        else if(!agent.isPresent()) {
            return ResponseHandler.generateResponse("User not found !", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                a = area.get();
                a.setDataEntryAgent(agent.get());
                a = areaRepository.save(a);
                return ResponseHandler.generateResponse("Agent " + idAgent + " has been properly added " +
                        "as data entry agent to Area " + idArea + " !", HttpStatus.OK, a);
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }

*/

/*
    // ADD COMMUNICATION AGENT
    @Override
    public ResponseEntity<Object> addCommunicationAgent(Long idArea, Long idAgent) {

        // GET AREA & AGENT
        Optional<Area> area = areaRepository.findById(idArea);
        Optional<User> agent = userRepository.findById(idAgent);

        Area a = new Area();

        if(!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        }
        else if(!agent.isPresent()) {
            return ResponseHandler.generateResponse("User not found !", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                a = area.get();
                a.setCommunicationAgent(agent.get());
                a = areaRepository.save(a);
                return ResponseHandler.generateResponse("Agent " + idAgent + " has been properly added " +
                        "as communication agent to Area " + idArea + " !", HttpStatus.OK, a);
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }
*/


/*
    // REMOVE DATA ENTRY AGENT
    @Override
    public ResponseEntity<Object> removeDataEntryAgent(Long idArea) {

        // GET AREA & AGENT
        Optional<Area> area = areaRepository.findById(idArea);

        if(!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                Area a = area.get();
                if(a.getDataEntryAgent() != null) {
                    a.setDataEntryAgent(null);
                }
                a = areaRepository.save(a);
                return ResponseHandler.generateResponse("Data entry Agent has been properly removed " +
                        "from Area " + idArea + " !", HttpStatus.OK, a);
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }
*/


/*
    // REMOVE COMMUNICATION AGENT
    @Override
    public ResponseEntity<Object> removeCommunicationAgent(Long idArea) {


        // GET AREA & AGENT
        Optional<Area> area = areaRepository.findById(idArea);

        if(!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                Area a = area.get();
                if(a.getCommunicationAgent() != null) {
                    a.setCommunicationAgent(null);
                }
                a = areaRepository.save(a);
                return ResponseHandler.generateResponse("Communication agent has been properly removed " +
                        "from Area " + idArea + " !", HttpStatus.OK, a);
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }
*/


    // GET ALL AREA USERS
    @Override
    public ResponseEntity<Object> getAllAreaUsers(Long idArea) {

        // GET AREA
        Optional<Area> area = areaRepository.findById(idArea);

        try {
            return area.map((a) -> {
                List<User> users = new ArrayList<>();

                // GET ALL CLUBS OF AREA
                List<Club> clubList = new ArrayList<>(a.getClubs());

                // GET ALL USERS OF EACH CLUB
                clubList.forEach(c -> {
                    users.addAll(c.getMembers());
                });

                // USERS TO BE RETURNED
                List<UserRequest> userRequests = new ArrayList<>();
                List<Long> usersId = new ArrayList<>();
                users.forEach(u -> {
                    UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    userRequests.add(ur);
                    usersId.add(u.getId());
                });
                // return ResponseHandler.generateResponse("Area " + idArea + " members", HttpStatus.OK, userRequests);
                return ResponseHandler.generateResponse("Area " + idArea + " members", HttpStatus.OK , usersId);
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Area not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // GET CENTER OF AN AREA
    @Override
    public ResponseEntity<Object> getCenterOfArea(Long idArea) {

        // GET AREA
        Optional<Area> area = areaRepository.findById(idArea);

        // GET ALL AREAS
        List<Center> centerList = centerRepository.findAll();

        try {
            if(centerList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("No Center content !");
            }

            return area.map((a) -> {
                Center c = new Center();
                for(Center center : centerList) {
                    if(center.getAreas().contains(a)) {
                        c = center;
                    }
                }
                return ResponseHandler.generateResponse("Center owner of area " + idArea, HttpStatus.OK, c);
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Area not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findAreasByName(String name) {
        // GET ALL CLUBS
        try {
            List<Area> areas = areaRepository.findAreaByName(name);
            if (areas.isEmpty()) {
                return ResponseHandler.generateResponse("Clubs list", HttpStatus.NO_CONTENT, null);
            }

            return ResponseHandler.generateResponse("Clubs list", HttpStatus.OK, areas);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }
}
