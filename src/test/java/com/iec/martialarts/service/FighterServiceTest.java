package com.iec.martialarts.service;

import com.iec.martialarts.model.Fighter;
import com.iec.martialarts.repository.FighterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FighterServiceTest {

    @Mock
    private FighterRepository fighterRepository;

    @InjectMocks
    private FighterService fighterService;

    private Fighter fighter1;
    private Fighter fighter2;

    @BeforeEach
    void setUp() {
        fighter1 = new Fighter();
        fighter1.setId(1L);
        fighter1.setName("Bruce Lee");
        fighter1.setNationality("Hong Kong");
        fighter1.setAge(32);
        fighter1.setMartialArtStyle("Jeet Kune Do");
        fighter1.setBeltRank("Master");
        fighter1.setWins(100);
        fighter1.setLosses(0);
        fighter1.setWeightClass("Lightweight");

        fighter2 = new Fighter();
        fighter2.setId(2L);
        fighter2.setName("Anderson Silva");
        fighter2.setNationality("Brazil");
        fighter2.setAge(48);
        fighter2.setMartialArtStyle("Muay Thai");
        fighter2.setBeltRank("Black Belt");
        fighter2.setWins(34);
        fighter2.setLosses(11);
        fighter2.setWeightClass("Middleweight");
    }

    @Test
    void testGetAllFighters() {
        List<Fighter> fighters = Arrays.asList(fighter1, fighter2);
        when(fighterRepository.findAll()).thenReturn(fighters);

        List<Fighter> result = fighterService.getAllFighters();

        assertEquals(2, result.size());
        verify(fighterRepository, times(1)).findAll();
    }

    @Test
    void testGetFighterById_Success() {
        when(fighterRepository.findById(1L)).thenReturn(Optional.of(fighter1));

        Optional<Fighter> result = fighterService.getFighterById(1L);

        assertTrue(result.isPresent());
        assertEquals("Bruce Lee", result.get().getName());
        verify(fighterRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFighterById_NotFound() {
        when(fighterRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Fighter> result = fighterService.getFighterById(999L);

        assertFalse(result.isPresent());
        verify(fighterRepository, times(1)).findById(999L);
    }

    @Test
    void testCreateFighter() {
        when(fighterRepository.save(any(Fighter.class))).thenReturn(fighter1);

        Fighter result = fighterService.createFighter(fighter1);

        assertNotNull(result);
        assertEquals("Bruce Lee", result.getName());
        verify(fighterRepository, times(1)).save(any(Fighter.class));
    }

    @Test
    void testUpdateFighter_Success() {
        Fighter updatedDetails = new Fighter();
        updatedDetails.setName("Bruce Lee Updated");
        updatedDetails.setNationality("Hong Kong");
        updatedDetails.setAge(33);
        updatedDetails.setMartialArtStyle("Jeet Kune Do");
        updatedDetails.setBeltRank("Grand Master");
        updatedDetails.setWins(150);
        updatedDetails.setLosses(0);
        updatedDetails.setWeightClass("Lightweight");

        when(fighterRepository.findById(1L)).thenReturn(Optional.of(fighter1));
        when(fighterRepository.save(any(Fighter.class))).thenReturn(fighter1);

        Fighter result = fighterService.updateFighter(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Bruce Lee Updated", result.getName());
        assertEquals("Grand Master", result.getBeltRank());
        verify(fighterRepository, times(1)).findById(1L);
        verify(fighterRepository, times(1)).save(any(Fighter.class));
    }

    @Test
    void testUpdateFighter_NotFound() {
        when(fighterRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            fighterService.updateFighter(999L, fighter1);
        });

        verify(fighterRepository, times(1)).findById(999L);
        verify(fighterRepository, never()).save(any(Fighter.class));
    }

    @Test
    void testDeleteFighter_Success() {
        when(fighterRepository.findById(1L)).thenReturn(Optional.of(fighter1));
        doNothing().when(fighterRepository).delete(fighter1);

        fighterService.deleteFighter(1L);

        verify(fighterRepository, times(1)).findById(1L);
        verify(fighterRepository, times(1)).delete(fighter1);
    }

    @Test
    void testDeleteFighter_NotFound() {
        when(fighterRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            fighterService.deleteFighter(999L);
        });

        verify(fighterRepository, times(1)).findById(999L);
        verify(fighterRepository, never()).delete(any(Fighter.class));
    }

    @Test
    void testGetFightersByMartialArt() {
        List<Fighter> fighters = Arrays.asList(fighter1);
        when(fighterRepository.findByMartialArtStyle("Jeet Kune Do")).thenReturn(fighters);

        List<Fighter> result = fighterService.getFightersByMartialArt("Jeet Kune Do");

        assertEquals(1, result.size());
        assertEquals("Jeet Kune Do", result.get(0).getMartialArtStyle());
        verify(fighterRepository, times(1)).findByMartialArtStyle("Jeet Kune Do");
    }

    @Test
    void testGetFightersByNationality() {
        List<Fighter> fighters = Arrays.asList(fighter2);
        when(fighterRepository.findByNationality("Brazil")).thenReturn(fighters);

        List<Fighter> result = fighterService.getFightersByNationality("Brazil");

        assertEquals(1, result.size());
        assertEquals("Brazil", result.get(0).getNationality());
        verify(fighterRepository, times(1)).findByNationality("Brazil");
    }
}
