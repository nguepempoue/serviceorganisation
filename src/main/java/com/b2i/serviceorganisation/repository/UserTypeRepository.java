package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

    @Query("select u from UserType u where u.label like concat(:label,'%')")
    List<UserType> findUserTypeByLabel(String label);
}
