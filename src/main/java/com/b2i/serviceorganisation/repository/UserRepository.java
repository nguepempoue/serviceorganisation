package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.constant.StatusUserEnum;
import com.b2i.serviceorganisation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUserNameOrEmail(String userName, String email);

    Optional<User> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String userName);

    Boolean existsByPhoneNumber(String phoneNumber);

    @Query("select u from User u where u.lastName like concat(:lastName,'%') and u.statusUserEnum=:statusUserEnum")
    List<User> findUsersByLastName(String lastName, StatusUserEnum statusUserEnum);

    @Query("select u from User u join u.roles r where r.name=:name and u.statusUserEnum=:statusUserEnum")
    List<User> findUsersByRole(String name, StatusUserEnum statusUserEnum);

    @Query("select u from User u where u.statusUserEnum=:statusUserEnum")
    List<User> findAllActiveUsers(StatusUserEnum statusUserEnum);

    @Query("select u from User u where u.userType.id=:id")
    List<User> findByIdType(Long id);

    @Query("select u from User u where u.userCategory.id=:id")
    List<User> findByIdCategory(Long id);

    @Query("select u from User u where u.firstName like concat(:firstName,'%') and u.statusUserEnum=:statusUserEnum")
    List<User> findUsersByFirstName(String firstName, StatusUserEnum statusUserEnum);

    @Query("select u from User u where u.phoneNumber like concat(:phoneNumber,'%') and u.statusUserEnum=:statusUserEnum")
    List<User> findUsersByPhoneNumber(String phoneNumber, StatusUserEnum statusUserEnum);
}
