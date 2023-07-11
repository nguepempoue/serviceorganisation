package com.b2i.serviceorganisation.service;

import com.b2i.serviceorganisation.Utils.Utils;
import com.b2i.serviceorganisation.constant.StatusUserEnum;
import com.b2i.serviceorganisation.dto.request.BeneficiaryRequest;
import com.b2i.serviceorganisation.dto.request.UserRequest;
import com.b2i.serviceorganisation.dto.response.ResponseHandler;
import com.b2i.serviceorganisation.model.*;
import com.b2i.serviceorganisation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserTypeRepository userTypeRepository;

    private final UserCategoryRepository userCategoryRepository;

    private final ClubRepository clubRepository;

    private final AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountServiceImplementation accountServiceImplementation;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private BeneficiaryServiceImplementation beneficiaryServiceImplementation;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Autowired
    private CivilityRepository civilityRepository;

    @Autowired
    private PieceTypeRepository pieceTypeRepository;

    @Autowired
    private FamilySituationRepository familySituationRepository;

    @Autowired
    private CountryRepository countryRepository;

    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository, UserTypeRepository userTypeRepository, UserCategoryRepository userCategoryRepository, ClubRepository clubRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userTypeRepository = userTypeRepository;
        this.userCategoryRepository = userCategoryRepository;
        this.clubRepository = clubRepository;
        this.accountRepository = accountRepository;
    }


    // GLOBAL USER CREATION
    private ResponseEntity<Object> createUser(UserRequest userRequest, String userType, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry,Long idUserType, Long idUserCategory){
        // GET CIVILITY
        Optional<Civility> civility = civilityRepository.findById(idCivility);

        // GET PIECE TYPE
        Optional<PieceType> pieceType = pieceTypeRepository.findById(idPieceType);

        // GET FAMILY SITUATION
        Optional<FamilySituation> situation = familySituationRepository.findById(idFamilySituation);

        // GET COUNTRY
        Optional<Country> country = countryRepository.findById(idCountry);

        // TEST EXISTENCE OF PROPERTIES
        if (userRepository.existsByUserName(userRequest.getUserName())) {
            return ResponseHandler.generateResponse("This username is already taken !", HttpStatus.BAD_REQUEST, null);
        }

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseHandler.generateResponse("This email is already taken !", HttpStatus.BAD_REQUEST, null);
        }

        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            return ResponseHandler.generateResponse("This phone number is already taken !", HttpStatus.BAD_REQUEST, null);
        }


        // SAVING MEMBER AND RETURN RESPONSE
        try {

            // CREATE NEW MEMBER
            User user = new User();

            // TEST OF STRING VALUES
            checkStringValuesOfUserRequest(userRequest);

            // TESTS ON OBJECT
            if(!civility.isPresent()) { return ResponseHandler.generateNotFoundResponse("Civility not found !"); } // CIVILITY
            if(!pieceType.isPresent()) { return ResponseHandler.generateNotFoundResponse("Piece type not found !"); } // PIECE TYPE
            if(!situation.isPresent()) { return ResponseHandler.generateNotFoundResponse("Family situation not found !"); } // FAMILY SITUATION
            if(!country.isPresent()) { return ResponseHandler.generateNotFoundResponse("Country not found !"); }


            if(userRequest.getDateOfIssue() == null) { throw new Exception("Date of issue can't be null or empty string !"); } // DATE OF ISSUE
            if(userRequest.getDateOfValidity() == null) { throw new Exception("Date of validity can't be null !"); } // DATE OF VALIDITY
            if(userRequest.getNumberOfChildren() == null) { throw new Exception("Number of children can't be null !"); } // NUMBER OF CHILDREN

            // SETTING VALUES
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPhoneNumber(userRequest.getPhoneNumber());
            user.setEmail(userRequest.getEmail());
            user.setUserName(userRequest.getUserName());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            //user.setPassword(userRequest.getPassword());
            user.setNationality(userRequest.getNationality());
            user.setCity(userRequest.getCity());
            user.setPieceId(userRequest.getPieceId());
            user.setPlaceOfIssue(userRequest.getPlaceOfIssue());
            user.setDateOfIssue(userRequest.getDateOfIssue());
            user.setDateOfValidity(userRequest.getDateOfValidity());
            user.setNationalIDNumber(userRequest.getNationalIDNumber());
            user.setMainAddress(userRequest.getMainAddress());
            user.setPostalBox(userRequest.getPostalBox());
            user.setNumberOfChildren(userRequest.getNumberOfChildren());
            user.setWhatsappPhoneNumber(userRequest.getWhatsappPhoneNumber());

            user.setCivility(civility.get());
            user.setPieceType(pieceType.get());
            user.setFamilySituation(situation.get());
            user.setCountry(country.get());
            user.setStatusUserEnum(StatusUserEnum.ACTIVE);

            //VERIFIER IF ID USER TYPE AND ID USER CATEGORY ARE NOT NULL
            if(idUserType != null && idUserCategory != null){
                //GET USER TYPE
                Optional<UserType> type = userTypeRepository.findById(idUserType);

                //GET USER CATEGORY
                Optional<UserCategory> category = userCategoryRepository.findById(idUserCategory);

                if(!type.isPresent()) { return ResponseHandler.generateNotFoundResponse("TYpe not found !"); }
                if(!category.isPresent()) { return ResponseHandler.generateNotFoundResponse("category not found !"); }

                user.setUserType(type.get());
                user.setUserCategory(category.get());
            }

            // OPTIONAL VALUES
            if(userRequest.getSecondaryEmail() != null) {
                user.setSecondaryEmail(userRequest.getSecondaryEmail());
            } // SECONDARY EMAIL
            if(userRequest.getSecondPhoneNumber() != null) {
                user.setSecondPhoneNumber(userRequest.getSecondPhoneNumber());
            } // SECOND PHONE NUMBER
            if(userRequest.getSecondaryAddress() != null) {
                user.setSecondaryAddress(userRequest.getSecondaryAddress());
            } // SECONDARY ADDRESS

            // OTHERS
            if(statusRepository.findById(1L).isPresent()) {
                user.setStatus(statusRepository.findById(1L).get());
            } // STATUS
            if (roleRepository.findRoleByName(userType).isPresent()) {
                user.getRoles().add(roleRepository.findRoleByName(userType).get());
            } // ROLES




            // SAVING USER
            User responseUser = userRepository.save(user);

            // GENERATE ACCOUNTS
            if(userType.equals("MUTUALIST") || userType.equals("MEMBER")) {
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 1L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 2L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 3L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 4L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 5L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 6L));
            }

/*            if(userType.equals("MEMBER")) {
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 1L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 2L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 3L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 4L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 5L));
                responseUser.getAccounts().add(accountServiceImplementation.createAccount(responseUser.getId(), 6L));
            }*/

            // UPDATE USER
            responseUser = userRepository.save(responseUser);

            // CHOOSING SPONSOR
            addSponsoredUsers(idSponsor, responseUser.getId());

            return ResponseHandler.generateResponse("This user has been saved !", HttpStatus.CREATED, responseUser);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // CREATE MEMBER
    @Override
    public ResponseEntity<Object> createUserMember(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry) {
        return createUser(userRequest, "MEMBER", idSponsor, idCivility, idPieceType, idFamilySituation, idCountry, null, null);
    }


    // CREATE MUTUALIST
    @Override
    public ResponseEntity<Object> createUserMutualist(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry, Long idType, Long idCategory) {
        return createUser(userRequest, "MUTUALIST", idSponsor, idCivility, idPieceType, idFamilySituation, idCountry, idType, idCategory);
    }


    // CREATE ADMIN
    @Override
    public ResponseEntity<Object> createUserAdmin(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry) {
        return createUser(userRequest, "ADMIN", idSponsor, idCivility, idPieceType, idFamilySituation, idCountry, null, null);
    }


    // CREATE OPERATOR
    @Override
    public ResponseEntity<Object> createUserOperator(UserRequest userRequest, Long idSponsor, Long idCivility, Long idPieceType, Long idFamilySituation, Long idCountry) {
        return createUser(userRequest, "OPERATOR", idSponsor, idCivility, idPieceType, idFamilySituation, idCountry, null, null);
    }


    // READ
    @Override
    public ResponseEntity<Object> findAllUsers() {

        // GET LIST OF ALL MEMBERS
        try {
            List<User> users = userRepository.findAllActiveUsers(StatusUserEnum.ACTIVE);
            List<User> filteredUsers = new ArrayList<>();

            if (users.isEmpty()) {
                return ResponseHandler.generateResponse("Members list", HttpStatus.NO_CONTENT, null);
            }

            users.forEach(u -> {
                if (!u.getRoles().get(0).getLabel().equals("ACTUATOR")) {
                    // UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    filteredUsers.add(u);
                }
            });
            return ResponseHandler.generateResponse("Members list", HttpStatus.OK, filteredUsers);
        }
        // IN CASE THERE'S AN ERROR
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // UPDATE
    @Override
    public ResponseEntity<Object> updateUser(Long id, UserRequest userRequest) {

        // GET THE MEMBER TO UPDATE
        Optional<User> user = userRepository.findById(id);
        User u = new User();

        // IF MEMBER IS PRESENT OR NOT
        try {
            if (user.isPresent()) {
                u = user.get();



                if (userRepository.existsByUserName(userRequest.getUserName()) && !u.getUserName().equals(userRequest.getUserName())) {
                    return ResponseHandler.generateResponse("This username is already taken !", HttpStatus.BAD_REQUEST, null);
                }

                if (userRepository.existsByEmail(userRequest.getEmail()) && !u.getEmail().equals(userRequest.getEmail())) {
                    return ResponseHandler.generateResponse("This email is already taken !", HttpStatus.BAD_REQUEST, null);
                }

                if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber()) && !u.getPhoneNumber().equals(userRequest.getPhoneNumber())) {
                    return ResponseHandler.generateResponse("This phone number is already taken !", HttpStatus.BAD_REQUEST, null);
                }

                // NAME
                if(userRequest.getFirstName() != null) {
                    u.setFirstName(userRequest.getFirstName());
                }

                if(userRequest.getLastName() != null) {
                    u.setLastName(userRequest.getLastName());
                }

                // CONTACTS
                if(userRequest.getEmail() != null) {
                    u.setEmail(userRequest.getEmail());
                }

                if(userRequest.getPhoneNumber() != null) {
                    u.setPhoneNumber(userRequest.getPhoneNumber());
                }

                // CREDENTIALS
                if(userRequest.getUserName() != null) {
                    u.setUserName(userRequest.getUserName());
                }

                if(userRequest.getPassword() != null) {
                    // u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
                    u.setPassword(userRequest.getPassword());
                }

                if(userRequest.getCity() != null) {
                    u.setCity(userRequest.getCity());
                }

                u = userRepository.save(u);

                // RETURN VALUE
                /* userRequest.setId(u.getId());
                userRequest.setFirstName(u.getFirstName());
                userRequest.setLastName(u.getLastName());
                userRequest.setUserName(u.getUserName());
                userRequest.setPhoneNumber(u.getPhoneNumber());
                userRequest.setEmail(u.getEmail());
                userRequest.setCity(u.getCity()); */
            }

                return ResponseHandler.generateResponse("This member has been properly updated !", HttpStatus.OK, u);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // DELETE
    @Override
    public ResponseEntity<Object> deleteUserById(Long id) {

        // GET MEMBER
        Optional<User> user = userRepository.findById(id);

        // TRY DELETING MEMBER
        if (!user.isPresent()) {
            return ResponseHandler.generateResponse("This member doesn't exist or has already been deleted",
                    HttpStatus.NOT_FOUND, null);
        }

        try {
            userRepository.deleteById(id);
            System.out.println("This member has been deleted properly !");
            return ResponseHandler.generateResponse("This member has been deleted properly !", HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> addRoleToUser(Long idUser, Long idRole) {
        // GET USER & ROLE
        Optional<User> user = userRepository.findById(idUser);
        Optional<Role> role = roleRepository.findById(idRole);

        // TESTS
        try {
            // USER NOT PRESENT
            if (!user.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // ROLE NOT PRESENT
            else if (!role.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Area not found");
            } else {
                User u = user.get();
                if (!u.getRoles().contains(role.get())) {
                    u.getRoles().add(role.get());
                }
                if(role.get().getName().equals("MUTUALIST") || role.get().getName().equals("MEMBER")){
                    u.getAccounts().add(accountServiceImplementation.createAccount(u.getId(), 1L));
                    u.getAccounts().add(accountServiceImplementation.createAccount(u.getId(), 2L));
                    u.getAccounts().add(accountServiceImplementation.createAccount(u.getId(), 3L));
                    u.getAccounts().add(accountServiceImplementation.createAccount(u.getId(), 4L));
                    u.getAccounts().add(accountServiceImplementation.createAccount(u.getId(), 5L));
                    u.getAccounts().add(accountServiceImplementation.createAccount(u.getId(), 6L));
                }
                u = userRepository.save(u);
                return ResponseHandler.generateResponse("The role " + idRole + " has properly been " +
                        "added to user " + idUser + " !", HttpStatus.OK, u);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> removeRoleFromUser(Long idUser, Long idRole) {
        // GET USER & ROLE
        Optional<User> user = userRepository.findById(idUser);
        Optional<Role> role = roleRepository.findById(idRole);

        // TESTS
        try {
            // USER NOT PRESENT
            if (!user.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Center not found");
            }
            // ROLE NOT PRESENT
            else if (!role.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Area not found");
            } else {
                User u = user.get();
                u.getRoles().remove(role.get());

                if(role.get().getName().equals("MUTUALIST") || role.get().getName().equals("MEMBER")){
                    u.getAccounts().clear();
                }
                u = userRepository.save(u);
                return ResponseHandler.generateResponse("The role " + idRole + " has properly been " +
                        "removed from user " + idUser + " !", HttpStatus.OK, u);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND BY ID
    @Override
    public ResponseEntity<Object> findUserById(Long id) {

        // GET THE OPTIONAL MEMBER
        Optional<User> user = userRepository.findById(id);
        // UserRequest userRequest = new UserRequest();

        try {

            // IF MEMBER EXISTS OR NOT
            if (!user.isPresent()) {
                return ResponseHandler.generateResponse("User " + id, HttpStatus.NOT_FOUND, null);
            }

            User u = user.get();

            // RETURN MEMBER DTO
            /* userRequest.setFirstName(u.getFirstName());
            userRequest.setLastName(u.getLastName());
            userRequest.setUserName(u.getUserName());
            userRequest.setPhoneNumber(u.getPhoneNumber());
            userRequest.setEmail(u.getEmail());
            userRequest.setId(u.getId());
            userRequest.setCity(u.getCity()); */

            return ResponseHandler.generateResponse("Membre : " + id, HttpStatus.OK, u);
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // COUNT ALL
    @Override
    public ResponseEntity<Object> countAll() {
        final Long usersNumber = userRepository.count();
        return ResponseHandler.generateResponse("Members total number", HttpStatus.OK, usersNumber);
    }


    // ADD SPONSORED MEMBERS
    @Override
    public ResponseEntity<Object> addSponsoredUsers(Long id, Long idToAdd) {

        // GET MEMBER
        Optional<User> user = userRepository.findById(id);
        Optional<User> userToAdd = userRepository.findById(idToAdd);

        // IF MEMBER IS PRESENT
        try {
            if (!user.isPresent()) {
                return ResponseHandler.generateResponse("User not found", HttpStatus.NOT_FOUND, null);
            } else if (!userToAdd.isPresent()) {
                return ResponseHandler.generateResponse("User to add not found", HttpStatus.NOT_FOUND, null);
            } else {
                user.get().getSponsoredUsers().add(userToAdd.get());
                User u = userRepository.save(user.get());

                // UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());

                return ResponseHandler.generateResponse("User " + idToAdd + " has " +
                        "been added to sponsored members of member " + id + " !", HttpStatus.OK, u);
            }
        } catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL USER ACCOUNTS
    @Override
    public ResponseEntity<Object> findAllUserAccounts(Long id) {

        // GET USER
        Optional<User> user = userRepository.findById(id);

        try {

            // ON OPTIONAL
            return user.map((value) -> {

                // GET ALL ACCOUNTS
                Set<Account> accountSet = new HashSet<>(value.getAccounts());
                return ResponseHandler.generateResponse("Accounts list of user " + id, HttpStatus.OK, accountSet);

            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("User not found !")); // RETURN NOT FOUND STATUS RESPONSE
        }
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ACCOUNT BY USER AND ACCOUNT TYPE
    @Override
    public ResponseEntity<Object> findAccountByUserAndAccountType(Long idUser, Long idAccountType) {

        // GET USER
        Optional<User> user = userRepository.findById(idUser);
        try {

            return user.map(u -> {

                // GET ALL USER ACCOUNTS
                List<Account> userAccounts = new ArrayList<>(u.getAccounts());

                // GET ACCOUNT TYPE
                Optional<AccountType> accountType = accountTypeRepository.findById(idAccountType);

                if(!accountType.isPresent()) {
                    return ResponseHandler.generateNotFoundResponse("Account type not found !");
                }

                Account account = null;

                // GET ACCOUNT OF THE TYPE
                for(Account a : userAccounts) {

                    // IF ACCOUNT TYPE ID == idAccountType, GET
                    if(a.getType().equals(accountType.get())) {

                        account = a;
                        break;
                    }
                }

                // IF ACCOUNT IS NULL
                if(account == null) {
                    return ResponseHandler.generateNotFoundResponse("Account not found !");
                }

                // ELSE
                return ResponseHandler.generateOkResponse("User " + idUser + " account of type " + accountType.get().getLabel(), account);

            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("User not found !"));
        }
        catch (Exception e) {
            return Utils.catchException(e);
        }
    }


    // COUNT ALL
    @Override
    public Long countAllUsers() {
        return userRepository.count();
    }


    // SAVING USER
    @Override
    public User saveUser(User user) {

        // SAVING
        try {
            System.out.println("This member has been properly saved !");
            return userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }


    // GLOBAL FIND USER BY ROLE
    private ResponseEntity<Object> findAllUserByRoleLabel(String roleLabel) {

        // GET ALL USERS
        List<User> users = userRepository.findAll();

        // LIST FOR ALL ADMINS
        List<User> filteredList = new ArrayList<>();

        try {
            // FILTERING ALL USERS

            if(users.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("No users found !");
            }

            users.forEach((u) -> {

                if(u.getRoles().contains(roleRepository.findRoleByName(roleLabel).get())) {
                    // UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    filteredList.add(u);
                }
            });

            if(filteredList.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("No users of type : " + roleLabel + " !");
            }

            return ResponseHandler.generateResponse("Users list of : " + roleLabel + " ", HttpStatus.OK, filteredList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // FIND ALL ADMINS
    @Override
    public ResponseEntity<Object> findAllAdmins() {
        String r = "ADMIN";
        return findAllUserByRoleLabel(r);
    }


    // FIND ALL MEMBERS
    @Override
    public ResponseEntity<Object> findAllMembers() {
        String r = "MEMBER";
        return findAllUserByRoleLabel(r);
    }


    // FIND ALL MUTUALISTS
    @Override
    public ResponseEntity<Object> findAllMutualists() {
        String r = "MUTUALIST";
        return findAllUserByRoleLabel(r);
    }


    // FIND USER BY EMAIL
    @Override
    public ResponseEntity<Object> findUserByEmail(String email) {

        // GET USER
        Optional<User> user = userRepository.findUserByEmail(email);

        try {
            return user.map(u -> ResponseHandler.generateResponse("User of email : " + email, HttpStatus.OK, u))
            .orElseGet(() -> ResponseHandler.generateNotFoundResponse("User of email : " + email + " not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // GET SPONSOR OF USER
    @Override
    public ResponseEntity<Object> getSponsorOfUser(Long id) {

        // GET USER
        Optional<User> user = userRepository.findById(id);

        try {

            // IF NOT PRESENT
            if(!user.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found !");
            }

            // GET ALL USERS
            List<User> allUsers = userRepository.findAll();

            // FOR ALL USERS != USER
            // IF SELECTED USER S SPONSORED MEMBERS CONTAINS USER
            // RETURN S (SELECTED USER IS SPONSOR OF USER)
            for (User sponsor : allUsers) {
                User u = user.get();
                if(!u.equals(sponsor) && sponsor.getSponsoredUsers().contains(u)) {

                    // UserRequest ur = new UserRequest(sponsor.getId(), sponsor.getFirstName(), sponsor.getLastName(), sponsor.getUserName(), sponsor.getPhoneNumber(), sponsor.getEmail(), sponsor.getCity());
                    return ResponseHandler.generateResponse("Sponsor of user " + u.getId(), HttpStatus.OK, sponsor);
                }
            }

            // IF SPONSOR NOT FOUND
            return ResponseHandler.generateNotFoundResponse("User doesn't have any sponsor or a sponsor has not been added to him!");
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // CHANGE USER STATUS
    @Override
    public ResponseEntity<Object> changeUserStatus(Long idUser, Long idStatus) {

        // GET USER
        Optional<User> user = userRepository.findById(idUser);

        // GET STATUS
        Optional<Status> status = statusRepository.findById(idStatus);

        try {

            if(!status.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Status not found !");
            }

            Status s = status.get();

            return user.map(u -> {
                u.setStatus(s);
                return ResponseHandler.generateResponse("Status " + idStatus + " set to user " + idUser, HttpStatus.OK,
                        userRepository.save(u));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("User not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // ADD BENEFICIARY TO USER
    @Override
    public ResponseEntity<Object> addBeneficiary(Long idUser, BeneficiaryRequest beneficiaryRequest, Long idPieceType) {

        // GET USER
        Optional<User> user = userRepository.findById(idUser);

        try {

            if(!user.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("User not found !");
            }

            // ADDING BENEFICIARY TO USER
            User u = user.get();
            Beneficiary beneficiary = beneficiaryServiceImplementation.createBeneficiary(beneficiaryRequest, idPieceType);
            u.getBeneficiaries().add(beneficiary);

            // SAVE AND RETURN
            return ResponseHandler.generateOkResponse("Beneficiary added to user " + idUser + " !", userRepository.save(u));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // REMOVE BENEFICIARY
    @Override
    public ResponseEntity<Object> removeBeneficiary(Long idUser, Long idBeneficiary) {

        // GET USER
        Optional<User> user = userRepository.findById(idBeneficiary);

        // GET BENEFICIARY
        Optional<Beneficiary> beneficiary = beneficiaryRepository.findById(idBeneficiary);

        try {
            if(!beneficiary.isPresent()) {
                return ResponseHandler.generateNotFoundResponse("Beneficiary not found !");
            }

            Beneficiary b = beneficiary.get();

            return user.map(u -> {

                // REMOVE
                u.getBeneficiaries().remove(b);
                return ResponseHandler.generateOkResponse("Beneficiary " + idBeneficiary + " removed of user " + idUser + " !",
                        userRepository.save(u));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("User not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // GET BENEFICIARIES OF USERS
    @Override
    public ResponseEntity<Object> findAllBeneficiariesOfUser(Long idUser) {

        // GET USER
        Optional<User> user = userRepository.findById(idUser);

        try {

            return user.map(u -> {
                List<Beneficiary> beneficiaries = new ArrayList<>(u.getBeneficiaries());
                return ResponseHandler.generateOkResponse("User " + idUser + " beneficiaries", beneficiaries);
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("User not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // CHANGE CIVILITY
    @Override
    public ResponseEntity<Object> changeCivility(Long idUser, Long idCivility) {

        // GET USER
        Optional<User> user = userRepository.findById(idUser);

        // GET CIVILITY
        Optional<Civility> civility = civilityRepository.findById(idCivility);

        try {

            // USER
            return user.map(u -> {

                // CHECK CIVILITY
                if(!civility.isPresent()) {
                    return ResponseHandler.generateNotFoundResponse("Civility not found !");
                }

                u.setCivility(civility.get());
                return ResponseHandler.generateOkResponse("User " + idUser + " civility updated !",
                        userRepository.save(u));
            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("User not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }


    // CHANGE FAMILY SITUATION
    @Override
    public ResponseEntity<Object> changeFamilySituation(Long idUser, Long idFamilySituation) {

        // GET USER
        Optional<User> user = userRepository.findById(idUser);

        // GET CIVILITY
        Optional<FamilySituation> situation = familySituationRepository.findById(idFamilySituation);

        try {

            // USER
            return user.map(u -> {

                // CHECK CIVILITY
                if(!situation.isPresent()) {
                    return ResponseHandler.generateNotFoundResponse("Family situation not found !");
                }

                u.setFamilySituation(situation.get());
                return ResponseHandler.generateOkResponse("User " + idUser + " family situation updated !",
                        userRepository.save(u));

            }).orElseGet(() -> ResponseHandler.generateNotFoundResponse("User not found !"));
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    //GET USER WITH LASTNAME
    @Override
    public ResponseEntity<Object> findUsersByLastName(String lastName) {
        try {
            List<User> users = this.userRepository.findUsersByLastName(lastName, StatusUserEnum.ACTIVE);
            List<User> filteredUsers = new ArrayList<>();

            if (users.isEmpty()) {
                return ResponseHandler.generateResponse("Members list", HttpStatus.NO_CONTENT, null);
            }

            users.forEach(u -> {
                if (!u.getRoles().get(0).getLabel().equals("ACTUATOR")) {
                    // UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    filteredUsers.add(u);
                }
            });
            return ResponseHandler.generateResponse("users list", HttpStatus.OK, filteredUsers);
        }
        // IN CASE THERE'S AN ERROR
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findUsersByFirstName(String firstName) {
        try {
            List<User> users = this.userRepository.findUsersByFirstName(firstName, StatusUserEnum.ACTIVE);
            List<User> filteredUsers = new ArrayList<>();

            if (users.isEmpty()) {
                return ResponseHandler.generateResponse("Members list", HttpStatus.NO_CONTENT, null);
            }

            users.forEach(u -> {
                if (!u.getRoles().get(0).getLabel().equals("ACTUATOR")) {
                    // UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    filteredUsers.add(u);
                }
            });
            return ResponseHandler.generateResponse("users list", HttpStatus.OK, filteredUsers);
        }
        // IN CASE THERE'S AN ERROR
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findUsersByPhoneNumber(String phoneNumber) {
        try {
            List<User> users = this.userRepository.findUsersByPhoneNumber(phoneNumber, StatusUserEnum.ACTIVE);
            List<User> filteredUsers = new ArrayList<>();

            if (users.isEmpty()) {
                return ResponseHandler.generateResponse("Members list", HttpStatus.NO_CONTENT, null);
            }

            users.forEach(u -> {
                if (!u.getRoles().get(0).getLabel().equals("ACTUATOR")) {
                    // UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    filteredUsers.add(u);
                }
            });
            return ResponseHandler.generateResponse("users list", HttpStatus.OK, filteredUsers);
        }
        // IN CASE THERE'S AN ERROR
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }


    //GET USER WITH ROLE NAME
    @Override
    public ResponseEntity<Object> findUsersByRole(String name) {
        // GET LIST OF ALL MEMBERS
        try {
            List<User> users = this.userRepository.findUsersByRole(name, StatusUserEnum.ACTIVE);
            List<User> filteredUsers = new ArrayList<>();

            if (users.isEmpty()) {
                return ResponseHandler.generateResponse("Members list", HttpStatus.NO_CONTENT, null);
            }

            users.forEach(u -> {
                if (!u.getRoles().get(0).getLabel().equals("ACTUATOR")) {
                    // UserRequest ur = new UserRequest(u.getId(), u.getFirstName(), u.getLastName(), u.getUserName(), u.getPhoneNumber(), u.getEmail(), u.getCity());
                    filteredUsers.add(u);
                }
            });
            return ResponseHandler.generateResponse("users list", HttpStatus.OK, filteredUsers);
        }
        // IN CASE THERE'S AN ERROR
        catch (Exception e) {
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> disableUser(Long idUser) {
        //CHEC THE USER
       try {
           Optional<User> user = this.userRepository.findById(idUser);
           User u = new User();

           if(user.isPresent()){
               user.get().setStatusUserEnum(StatusUserEnum.DISABLED);
               u = this.userRepository.save(user.get());
           }
           return ResponseHandler.generateResponse("Updated user", HttpStatus.OK, u);
       }catch (Exception e){
           return ResponseHandler.generateError(e);
       }
    }

    @Override
    public ResponseEntity<Object> findUsersByIdType(Long idType) {
        // GET ALL USER TYPE
        List<User> users = userRepository.findByIdType(idType);

        try {
            if(users.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Users list is empty !");
            }
            return ResponseHandler.generateResponse("Users list", HttpStatus.OK, users);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> findUsersByIdCategory(Long idCategory) {
        // GET ALL USER TYPE
        List<User> users = userRepository.findByIdCategory(idCategory);
        System.out.println("::::::::::::::::::::::::::::::::::::: users ::::::::::::::::" + users);
        try {
            if(users.isEmpty()) {
                return ResponseHandler.generateNoContentResponse("Users list is empty !");
            }
            return ResponseHandler.generateResponse("Users list", HttpStatus.OK, users);
        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateError(e);
        }
    }

    @Override
    public ResponseEntity<Object> transferMemberToAnotherClub(Long idClub, Long idMember) {
        // GET MEMBER & CLUB
        Optional<User> member = userRepository.findById(idMember);
        Optional<Club> club = clubRepository.findById(idClub);
        Club actuClub = clubRepository.findClubByIdUser(idMember);

        if(actuClub == null) {
            return ResponseHandler.generateResponse("This member does not belong to any clubClub not found.", HttpStatus.NOT_FOUND, null);
        }
        if(!club.isPresent()) {
            return ResponseHandler.generateResponse("Club not found.", HttpStatus.NOT_FOUND, null);
        }
        else if(!member.isPresent()) {
            return ResponseHandler.generateResponse("User to transfer not found.", HttpStatus.NOT_FOUND, null);
        }
        else {
            try {

                Club c = club.get();
                if(c.getMembers().contains(member.get())){
                    return ResponseHandler.generateNoContentResponse("this member already belongs to this club");
                }
                actuClub.getMembers().remove(member.get());
                clubRepository.save(actuClub);
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




    /* **************************************** OTHER **************************************** */




    private void checkStringValuesOfUserRequest(UserRequest userRequest) throws Exception {

        // TESTS ON STRING VALUES
        Utils.checkStringValues(userRequest.getFirstName(), "First name"); // FIRSTNAME
        Utils.checkStringValues(userRequest.getLastName(), "Last name"); // LASTNAME
        Utils.checkStringValues(userRequest.getPhoneNumber(), "Phone number"); // PHONE NUMBER
        Utils.checkStringValues(userRequest.getEmail(), "Main email"); // MAIN EMAIL
        Utils.checkStringValues(userRequest.getUserName(), "Username"); // USERNAME
        Utils.checkStringValues(userRequest.getPassword(), "Password"); // PASSWORD
        Utils.checkStringValues(userRequest.getNationality(), "User nationality"); // NATIONALITY
        Utils.checkStringValues(userRequest.getCity(), "City"); // CITY
        Utils.checkStringValues(userRequest.getPieceId(), "Piece ID"); // PIECE ID
        Utils.checkStringValues(userRequest.getPlaceOfIssue(), "Place of issue"); // PLACE OF ISSUE
        Utils.checkStringValues(userRequest.getNationalIDNumber(), "National identification number"); // ID NUMBER
        Utils.checkStringValues(userRequest.getMainAddress(), "Main address"); // MAIN ADDRESS
        Utils.checkStringValues(userRequest.getPostalBox(), "Postal box"); // POSTAL BOX
        Utils.checkStringValues(userRequest.getWhatsappPhoneNumber(), "Whatsapp phone number"); // WHATSAPP PHONE NUMBER
    }
}
