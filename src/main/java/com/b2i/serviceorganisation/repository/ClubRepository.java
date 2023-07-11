package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {

    @Query("select c from Club c where c.name like concat(:name,'%')")
    List<Club> findClubsByName(String name);

    @Query("select c from Club c join c.users u where u.id=:idUser")
    Club findClubByIdUser(Long idUser);
}
