package com.iec.martialarts.repository;

import com.iec.martialarts.model.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FighterRepository extends JpaRepository<Fighter, Long> {
    
    List<Fighter> findByMartialArtStyle(String martialArtStyle);
    
    List<Fighter> findByNationality(String nationality);
    
    List<Fighter> findByWeightClass(String weightClass);
}
