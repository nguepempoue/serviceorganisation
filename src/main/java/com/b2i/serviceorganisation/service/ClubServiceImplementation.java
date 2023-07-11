package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.dto.request.ClubRequest;
import com.b2i.serviceorganisation.dto.request.UserRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.*;
import com.b2i.serviceorganisation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ClubServiceImplementation implements ClubService{

    private final ClubRepository clubRepository;

    private final UserRepository userRepository;

    private final AreaRepository areaRepository;

    private final StatusRepository statusRepository;

    private final PostRepository postRepository;

    private final PostServiceImplementation postServiceImplementation;

    public ClubServiceImplementation(ClubRepository clubRepository, UserRepository userRepository, AreaRepository areaRepository, StatusRepository statusRepository, PostRepository postRepository, PostServiceImplementation postServiceImplementation) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
        this.areaRepository = areaRepository;
        this.statusRepository = statusRepository;
        this.postRepository = postRepository;
        this.postServiceImplementation = postServiceImplementation;
    }


    @Autowired
    private AreaServiceImplementation areaServiceImplementation;


    // CREATE CLUB
    @Override
    public ResponseEntity<Object> createClub(ClubRequest clubRequest) {

        // CREATE CLUB
        Club club = new Club();
        //FIND ALL LIST OF POSTS IN CLUBS
        try {

            // CHECK NAME
            Utils.checkStringValues(clubRequest.getName(), "Club name");

            // CHECK REFERENCE
            Utils.checkStringValues(clubRequest.getReference(), "Club reference");

            // CHECK OBSERVATION
            Utils.checkStringValues(clubRequest.getObservation(), "Club observation");

            // SETTING VALUES
            club.setName(clubRequest.getName());
            club.setReference(clubRequest.getReference());
            club.setCreationDate(LocalDate.now());
            club.setObservation(clubRequest.getObservation());

            // STATUS
            if(statusRepository.findById(1L).isPresent()) {
                club.setStatus(statusRepository.findById(1L).get());
            }

            Club c = clubRepository.save(club);

            postServiceImplementation.createPostForLenelOrganisation("Pilote", 1, "description du poste", OrganisationLevelEnum.CLUB, c.getId());

            // ADD TO AREA
            //areaServiceImplementation.addClub(idArea, c.getId());
            return ResponseHandler.generateResponse("This club has been created properly !", HttpStatus.CREATED, c);

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return ResponseHandler.generateError(e);
        }
    }


/*    // ADD PILOT TO A CLUB
    @Override
    public ResponseEntity<Object> addPilot(Long idClub, Long idPilot) {

        // GET CLUB
        Optional<Club> club = clubRepository.findById(idClub);
        Optional<User> pilot = userRepository.findById(idPilot);

        if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found", HttpStatus.NOT_FOUND, null);
        }
        else if(!pilot.isPresent()) {
            return ResponseHandler.generateResponse("User pilot not found", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                club.get().setPilot(pilot.get());
                if (!club.get().getMembers().contains(pilot.get())) {
                    club.get().getMembers().add(pilot.get());
                }
                Club c = clubRepository.save(club.get());
                
                return ResponseHandler.generateResponse("User " + idPilot + " has been " +
                        "properly added as Club " + idClub + " pilot !", HttpStatus.OK, c);
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }*/


    // FIND ALL CLUB
    @Override
    public ResponseEntity<Object> findAllClubs() {

        // GET THE LIST OF ALL CLUBS
        try {
            List<Club> clubs = clubRepository.findAll();

            if(clubs.isEmpty()) {
                return ResponseHandler.generateResponse("Clubs List", HttpStatus.NO_CONTENT, clubs);
            }
            return ResponseHandler.generateResponse("Clubs list", HttpStatus.OK, clubs);
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE CLUB
    @Override
    public ResponseEntity<Object> updateClub(Long id, ClubRequest clubRequest) {

        // GET CLUB
        Optional<Club> club = clubRepository.findById(id);

        if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found.", HttpStatus.NOT_FOUND, null);
        }

        try {
            if(clubRequest.getName() != null) {
                club.get().setName(clubRequest.getName());
            } else {
                return ResponseHandler.generateResponse("The club name can't be null !", HttpStatus.BAD_REQUEST, null);
            }

            if(clubRequest.getReference() != null) {
                club.get().setReference(clubRequest.getReference());
            } else {
                return ResponseHandler.generateResponse("The club reference can't be null !", HttpStatus.BAD_REQUEST, null);
            }

            if(clubRequest.getObservation() != null) {
                club.get().setObservation(clubRequest.getObservation());
            } else {
                return ResponseHandler.generateResponse("The club observation can't be null !", HttpStatus.BAD_REQUEST, null);
            }

            Club c = clubRepository.save(club.get());
            return ResponseHandler.generateResponse("Club updated !", HttpStatus.OK, c);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE CLUB BY ID
    @Override
    public ResponseEntity<Object> deleteClubById(Long id) {

        // GET CLUB
        Optional<Club> club = clubRepository.findById(id);
        //GET AREA OF CLUB
        Area area = areaRepository.findAreaByIdClub(id);

        try {
            // IF CLUB IS NOT PRESENT
            if(!club.isPresent()) {
                return ResponseHandler.generateResponse("Club not found", HttpStatus.NOT_FOUND, null);
            }
            //REMOVE CLUB IN AREA
            if(area != null){
                area.getClubs().remove(club.get());
                this.areaRepository.save(area);
            }

            List<Post> posts = this.postRepository.finAllPostByIdClub(id);
            if (posts != null){
                posts.forEach((post)-> {
                    club.get().getPosts().remove(post);
                });
            }

            clubRepository.delete(club.get());
            return ResponseHandler.generateResponse("This club has been properly deleted !", HttpStatus.OK, null);
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // GET CLUB BY ID
    @Override
    public ResponseEntity<Object> findClubById(Long id) {

        // GET CLUB
        Optional<Club> club = clubRepository.findById(id);

        try {
            return club.map((c) -> ResponseHandler.generateResponse("Club : " + id, HttpStatus.OK, c))
                    .orElseGet(() -> ResponseHandler.generateNotFoundResponse("Club not found !"));
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL CLUBS
    @Override
    public ResponseEntity<Object> countAllClubs() {
        Long clubsNumber = clubRepository.count();
        return ResponseHandler.generateResponse("Clubs number", HttpStatus.OK, clubsNumber);
    }


    // ADD MEMBER
    @Override
    public ResponseEntity<Object> addMember(Long idClub, Long idMember) {

        // GET MEMBER & CLUB
        Optional<User> member = userRepository.findById(idMember);
        Optional<Club> club = clubRepository.findById(idClub);
        List<Club> clubs = this.clubRepository.findAll();
        AtomicBoolean isMember = new AtomicBoolean(false);
        clubs.forEach((club1 -> {
            if(club1.getMembers().contains(member.get())){
                isMember.set(true);
            }
        }));
        if(isMember.get()){
            return ResponseHandler.generateNoContentResponse("a member may belong to only one club");
        }
        if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found.", HttpStatus.NOT_FOUND, null);
        }
        else if(!member.isPresent()) {
            return ResponseHandler.generateResponse("User to add not found.", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                Club c = club.get();
                if(!c.getMembers().contains(member.get())){
                    c.getMembers().add(member.get());
                }
                return ResponseHandler.generateResponse("User " + idMember + " has been " +
                        "properly added to Club " + idClub + " members !", HttpStatus.OK, clubRepository.save(c));
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }


/*
    // REMOVE MEMBER
    @Override
    public ResponseEntity<Object> removeMember(Long idClub, Long idMember) {

        // GET MEMBER & CLUB
        Optional<User> member = userRepository.findById(idMember);
        Optional<Club> club = clubRepository.findById(idClub);

        if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found.", HttpStatus.NOT_FOUND, null);
        }
        else if(!member.isPresent()) {
            return ResponseHandler.generateResponse("User to add not found.", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                Club c = club.get();
                c.getMembers().remove(member.get());
                if(c.getPilot().equals(member.get())) {
                    c.setPilot(null);
                }
                return ResponseHandler.generateResponse("User " + idMember + " has been " +
                        "properly removed from Club " + idClub + " members !", HttpStatus.OK, clubRepository.save(c));
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }


    // REMOVE PILOT
    @Override
    public ResponseEntity<Object> removePilot(Long idClub) {

        // GET CLUB
        Optional<Club> club = clubRepository.findById(idClub);

        if(!club.isPresent()) {
            return ResponseHandler.generateNotFoundResponse("Club not found !");
        }
        else {
            try {
                Club c = club.get();
                Long idPilot = 0L;
                if(c.getPilot() != null) {
                    idPilot = c.getPilot().getId();
                    c.setPilot(null);
                }
                return ResponseHandler.generateResponse("User " + idPilot + " has been " +
                        "properly removed as pilot of Club " + idClub + " !", HttpStatus.OK, clubRepository.save(c));
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }
*/


    // GET ALL CLUB USERS
    @Override
    public ResponseEntity<Object> getAllClubUsers(Long idClub) {

        // GET CLUB
        Optional<Club> club = clubRepository.findById(idClub);

        try {
            return club.map(c -> {
                List<UserRequest> users = new ArrayList<>();
                List<Long> usersId = new ArrayList<>();
                c.getMembers().forEach(u -> {
                    UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    users.add(ur);
                    usersId.add(u.getId());
                });
                // return ResponseHandler.generateResponse("Club " + idClub + " members", HttpStatus.OK , users);
                return ResponseHandler.generateResponse("Club " + idClub + " members", HttpStatus.OK , usersId);
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Club not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // GET AREA OF CLUB
    @Override
    public ResponseEntity<Object> getAreaOfClub(Long idClub) {

        // GET CLUB
        Optional<Club> club = clubRepository.findById(idClub);

        // GET ALL AREAS
        List<Area> areaList = areaRepository.findAll();

        try {
            if(areaList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("No Area content !");
            }

            return club.map((c) -> {
                Area a = new Area();
                for(Area area : areaList) {
                    if(area.getClubs().contains(c)) {
                        a = area;
                    }
                }
                return ResponseHandler.generateResponse("Area owner of club " + idClub, HttpStatus.OK, a);
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Club not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // CHANGE CLUB STATUS
    @Override
    public ResponseEntity<Object> changeClubStatus(Long idClub, Long idStatus) {

        // GET USER
        Optional<Club> club = clubRepository.findById(idClub);

        // GET STATUS
        Optional<Status> status = statusRepository.findById(idStatus);

        try {

            if(!status.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Status not found !");
            }

            Status s = status.get();

            return club.map(c -> {
                c.setStatus(s);
                return ResponseHandler.generateResponse("Status " + idStatus + " set to club " + idClub, HttpStatus.OK,
                        clubRepository.save(c));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("Club not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    //GET CLUBS BY NAME
    @Override
    public ResponseEntity<Object> findClubsByName(String name) {
        // GET THE LIST OF ALL CLUBS
        try {
            List<Club> clubs = this.clubRepository.findClubsByName(name);

            if(clubs.isEmpty()) {
                return ResponseHandler.generateResponse("Clubs List", HttpStatus.NO_CONTENT, clubs);
            }
            return ResponseHandler.generateResponse("Clubs list", HttpStatus.OK, clubs);
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> transferClubToAnotherArea(Long idArea, Long idClub) {
        // GET AREA & CLUB
        Optional<Area> area = areaRepository.findById(idArea);
        Optional<Club> club = clubRepository.findById(idClub);
        Area actuArea = areaRepository.findAreaByIdClub(idClub);

        Area a;

        if(actuArea == null) {
            return ResponseHandler.generateResponse("This club does not belong to any area !", HttpStatus.NOT_FOUND, null);
        }
        if(!area.isPresent()) {
            return ResponseHandler.generateResponse("Area not found !", HttpStatus.NOT_FOUND, null);
        }
        else if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found !", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {
                a = area.get();
                if(a.getClubs().contains(club.get())){
                    return ResponseHandler.generateResponse("This club already belongs to this area !", HttpStatus.NOT_FOUND, null);
                }
                actuArea.getClubs().remove(club.get());
                areaRepository.save(actuArea);
                a.getClubs().add(club.get());
                return ResponseHandler.generateResponse("Club " + idClub + " has been properly transferred to Area "
                        + idArea + " !", HttpStatus.OK, areaRepository.save(a));
            }
            catch (Exception e) {
                return ResponseHandler.generateError(e);
            }
        }
    }
}
