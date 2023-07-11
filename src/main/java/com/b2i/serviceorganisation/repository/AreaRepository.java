package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("select a from Area a where a.name like concat(:name,'%')")
    List<Area> findAreaByName(String name);

    @Query("select a from Area a join a.clubs c where c.id=:idClub")
    Area findAreaByIdClub(Long idClub);
}
