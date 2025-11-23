package com.iec.martialarts.service;

import com.iec.martialarts.model.Fighter;
import com.iec.martialarts.repository.FighterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FighterService {

    @Autowired
    private FighterRepository fighterRepository;

    public List<Fighter> getAllFighters() {
        return fighterRepository.findAll();
    }

    public Optional<Fighter> getFighterById(Long id) {
        return fighterRepository.findById(id);
    }

    public List<Fighter> getFightersByMartialArt(String martialArtStyle) {
        return fighterRepository.findByMartialArtStyle(martialArtStyle);
    }

    public List<Fighter> getFightersByNationality(String nationality) {
        return fighterRepository.findByNationality(nationality);
    }

    public Fighter createFighter(Fighter fighter) {
        return fighterRepository.save(fighter);
    }

    public Fighter updateFighter(Long id, Fighter fighterDetails) {
        Fighter fighter = fighterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lutador não encontrado com id: " + id));

        fighter.setName(fighterDetails.getName());
        fighter.setNationality(fighterDetails.getNationality());
        fighter.setAge(fighterDetails.getAge());
        fighter.setMartialArtStyle(fighterDetails.getMartialArtStyle());
        fighter.setBeltRank(fighterDetails.getBeltRank());
        fighter.setWins(fighterDetails.getWins());
        fighter.setLosses(fighterDetails.getLosses());
        fighter.setWeightClass(fighterDetails.getWeightClass());

        return fighterRepository.save(fighter);
    }

    public void deleteFighter(Long id) {
        Fighter fighter = fighterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lutador não encontrado com id: " + id));
        fighterRepository.delete(fighter);
    }
}
