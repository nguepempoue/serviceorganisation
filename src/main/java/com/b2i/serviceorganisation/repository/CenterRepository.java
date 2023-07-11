package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {

    @Query("select c from Center c where c.name like concat(:name,'%')")
    List<Center> findCenterByName(String name);

    @Query("select c from Center c join c.areas a where a.id=:idArea")
    Center findCenterByIdArea(Long idArea);
}
