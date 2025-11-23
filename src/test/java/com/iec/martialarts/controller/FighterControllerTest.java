package com.iec.martialarts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iec.martialarts.model.Fighter;
import com.iec.martialarts.service.FighterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(FighterController.class)
class FighterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
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

    /**
     * Teste para Rota 1: GET /api/fighters - Listar todos os lutadores
     */
    @Test
    void testGetAllFighters() throws Exception {
        List<Fighter> fighters = Arrays.asList(fighter1, fighter2);
        when(fighterService.getAllFighters()).thenReturn(fighters);

        mockMvc.perform(get("/api/fighters"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Bruce Lee")))
                .andExpect(jsonPath("$[1].name", is("Anderson Silva")));

        verify(fighterService, times(1)).getAllFighters();
    }

    /**
     * Teste para Rota 2: GET /api/fighters/{id} - Buscar lutador por ID
     */
    @Test
    void testGetFighterById_Success() throws Exception {
        when(fighterService.getFighterById(1L)).thenReturn(Optional.of(fighter1));

        mockMvc.perform(get("/api/fighters/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Bruce Lee")))
                .andExpect(jsonPath("$.martialArtStyle", is("Jeet Kune Do")));

        verify(fighterService, times(1)).getFighterById(1L);
    }

    @Test
    void testGetFighterById_NotFound() throws Exception {
        when(fighterService.getFighterById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/fighters/999"))
                .andExpect(status().isNotFound());

        verify(fighterService, times(1)).getFighterById(999L);
    }

    /**
     * Teste para Rota 3: POST /api/fighters - Criar novo lutador
     */
    @Test
    void testCreateFighter_Success() throws Exception {
        Fighter newFighter = new Fighter();
        newFighter.setName("Conor McGregor");
        newFighter.setNationality("Ireland");
        newFighter.setAge(35);
        newFighter.setMartialArtStyle("MMA");
        newFighter.setBeltRank("Brown Belt");
        newFighter.setWins(22);
        newFighter.setLosses(6);
        newFighter.setWeightClass("Lightweight");

        Fighter savedFighter = new Fighter();
        savedFighter.setId(3L);
        savedFighter.setName(newFighter.getName());
        savedFighter.setNationality(newFighter.getNationality());
        savedFighter.setAge(newFighter.getAge());
        savedFighter.setMartialArtStyle(newFighter.getMartialArtStyle());
        savedFighter.setBeltRank(newFighter.getBeltRank());
        savedFighter.setWins(newFighter.getWins());
        savedFighter.setLosses(newFighter.getLosses());
        savedFighter.setWeightClass(newFighter.getWeightClass());

        when(fighterService.createFighter(any(Fighter.class))).thenReturn(savedFighter);

        mockMvc.perform(post("/api/fighters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newFighter)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Conor McGregor")))
                .andExpect(jsonPath("$.nationality", is("Ireland")));

        verify(fighterService, times(1)).createFighter(any(Fighter.class));
    }

    @Test
    void testCreateFighter_InvalidData() throws Exception {
        Fighter invalidFighter = new Fighter();
        // Nome vazio - deve falhar na validação

        mockMvc.perform(post("/api/fighters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidFighter)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Teste para Rota Extra: PUT /api/fighters/{id} - Atualizar lutador
     */
    @Test
    void testUpdateFighter_Success() throws Exception {
        Fighter updatedFighter = new Fighter();
        updatedFighter.setId(1L);
        updatedFighter.setName("Bruce Lee");
        updatedFighter.setNationality("Hong Kong");
        updatedFighter.setAge(32);
        updatedFighter.setMartialArtStyle("Jeet Kune Do");
        updatedFighter.setBeltRank("Grand Master");
        updatedFighter.setWins(150);
        updatedFighter.setLosses(0);
        updatedFighter.setWeightClass("Lightweight");

        when(fighterService.updateFighter(eq(1L), any(Fighter.class))).thenReturn(updatedFighter);

        mockMvc.perform(put("/api/fighters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFighter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.beltRank", is("Grand Master")))
                .andExpect(jsonPath("$.wins", is(150)));

        verify(fighterService, times(1)).updateFighter(eq(1L), any(Fighter.class));
    }

    /**
     * Teste para Rota Extra: DELETE /api/fighters/{id} - Deletar lutador
     */
    @Test
    void testDeleteFighter_Success() throws Exception {
        doNothing().when(fighterService).deleteFighter(1L);

        mockMvc.perform(delete("/api/fighters/1"))
                .andExpect(status().isNoContent());

        verify(fighterService, times(1)).deleteFighter(1L);
    }

    @Test
    void testDeleteFighter_NotFound() throws Exception {
        doThrow(new RuntimeException("Lutador não encontrado")).when(fighterService).deleteFighter(999L);

        mockMvc.perform(delete("/api/fighters/999"))
                .andExpect(status().isNotFound());

        verify(fighterService, times(1)).deleteFighter(999L);
    }

    /**
     * Teste para Rota Extra: GET /api/fighters/style/{style} - Buscar por estilo
     */
    @Test
    void testGetFightersByMartialArt() throws Exception {
        List<Fighter> fighters = Arrays.asList(fighter1);
        when(fighterService.getFightersByMartialArt("Jeet Kune Do")).thenReturn(fighters);

        mockMvc.perform(get("/api/fighters/style/Jeet Kune Do"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].martialArtStyle", is("Jeet Kune Do")));

        verify(fighterService, times(1)).getFightersByMartialArt("Jeet Kune Do");
    }

    /**
     * Teste para Rota Extra: GET /api/fighters/nationality/{nationality} - Buscar por nacionalidade
     */
    @Test
    void testGetFightersByNationality() throws Exception {
        List<Fighter> fighters = Arrays.asList(fighter2);
        when(fighterService.getFightersByNationality("Brazil")).thenReturn(fighters);

        mockMvc.perform(get("/api/fighters/nationality/Brazil"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nationality", is("Brazil")));

        verify(fighterService, times(1)).getFightersByNationality("Brazil");
    }
}
