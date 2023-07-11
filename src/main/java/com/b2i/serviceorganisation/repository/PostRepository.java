package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.constant.OrganisationLevelEnum;
import com.b2i.serviceorganisation.model.Area;
import com.b2i.serviceorganisation.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.organisationLevelEnum=:organisationLevelEnum")
    List<Post> finAllPostByOrganisationLevel(OrganisationLevelEnum organisationLevelEnum);

    @Query("select p from Post p where p.mainOffice.id=:idMainOffice")
    List<Post> finAllPostByIdMainOffice(Long idMainOffice);

    @Query("select p from Post p where p.center.id=:idCenter")
    List<Post> finAllPostByIdCenter(Long idCenter);

    @Query("select p from Post p where p.area.id=:idArea")
    List<Post> finAllPostByIdArea(Long idArea);

    @Query("select p from Post p where p.club.id=:idClub")
    List<Post> finAllPostByIdClub(Long idClub);

    @Query("select p from Post p where p.name like concat(:name,'%')")
    List<Post> findPostByName(String name);
}
