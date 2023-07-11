package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

    @Query("select u from UserCategory u where u.name like concat(:name,'%')")
    List<UserCategory> findUserCategoryByName(String name);
}
