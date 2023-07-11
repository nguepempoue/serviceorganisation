package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.FamilySituation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilySituationRepository extends JpaRepository<FamilySituation, Long> {

    @Query("select f from FamilySituation f where f.label like concat(:label,'%')")
    List<FamilySituation> findFamilySituationByLabel(String label);
}
