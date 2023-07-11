package com.b2i.serviceorganisation.repository;

import com.b2i.serviceorganisation.model.PieceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PieceTypeRepository extends JpaRepository<PieceType, Long> {

    @Query("select p from PieceType p where p.label like concat(:label,'%')")
    List<PieceType> findPieceTypeByLabel(String label);
}
