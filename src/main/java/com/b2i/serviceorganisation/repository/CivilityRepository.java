package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.Civility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CivilityRepository extends JpaRepository<Civility, Long> {

    @Query("select c from Civility c where c.name like concat(:name,'%')")
    List<Civility> findCivilityByName(String name);
}
