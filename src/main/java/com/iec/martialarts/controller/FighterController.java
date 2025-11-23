package com.iec.martialarts.controller;

import com.iec.martialarts.model.Fighter;
import com.iec.martialarts.service.FighterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fighters")
public class FighterController {

    @Autowired
    private FighterService fighterService;

    /**
     * Rota 1: Listar todos os lutadores
     * GET /api/fighters
     */
    @GetMapping
    public ResponseEntity<List<Fighter>> getAllFighters() {
        List<Fighter> fighters = fighterService.getAllFighters();
        return ResponseEntity.ok(fighters);
    }

    /**
     * Rota 2: Buscar lutador por ID
     * GET /api/fighters/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fighter> getFighterById(@PathVariable Long id) {
        return fighterService.getFighterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Rota 3: Criar novo lutador
     * POST /api/fighters
     */
    @PostMapping
    public ResponseEntity<Fighter> createFighter(@Valid @RequestBody Fighter fighter) {
        Fighter createdFighter = fighterService.createFighter(fighter);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFighter);
    }

    /**
     * Rota Extra: Atualizar lutador existente
     * PUT /api/fighters/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fighter> updateFighter(@PathVariable Long id, @Valid @RequestBody Fighter fighterDetails) {
        try {
            Fighter updatedFighter = fighterService.updateFighter(id, fighterDetails);
            return ResponseEntity.ok(updatedFighter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Rota Extra: Deletar lutador
     * DELETE /api/fighters/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFighter(@PathVariable Long id) {
        try {
            fighterService.deleteFighter(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Rota Extra: Buscar lutadores por estilo de luta marcial
     * GET /api/fighters/style/{style}
     */
    @GetMapping("/style/{style}")
    public ResponseEntity<List<Fighter>> getFightersByMartialArt(@PathVariable String style) {
        List<Fighter> fighters = fighterService.getFightersByMartialArt(style);
        return ResponseEntity.ok(fighters);
    }

    /**
     * Rota Extra: Buscar lutadores por nacionalidade
     * GET /api/fighters/nationality/{nationality}
     */
    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<Fighter>> getFightersByNationality(@PathVariable String nationality) {
        List<Fighter> fighters = fighterService.getFightersByNationality(nationality);
        return ResponseEntity.ok(fighters);
    }
}
