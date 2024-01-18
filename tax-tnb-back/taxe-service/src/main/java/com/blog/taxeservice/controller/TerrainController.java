package com.blog.taxeservice.controller;

import com.blog.taxeservice.model.Terrain;
import com.blog.taxeservice.service.TerrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/main/terrain")
@RequiredArgsConstructor
public class TerrainController {
    private final TerrainService terrainService;


    @PostMapping("/save")
    public <S extends Terrain> S save(@RequestBody S entity) {
        return terrainService.save(entity);
    }

    @GetMapping("/findAll")
    public List<Terrain> findAll() {
        return terrainService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Terrain> findById(@PathVariable Long id) {
        return terrainService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable Long id) {
        terrainService.deleteById(id);
    }

    @GetMapping("/nom/{terrainId}/details")
    public List<Object[]> getNomByTerrainId(@PathVariable Long terrainId) {
        return terrainService.getNomByTerrainId(terrainId);
    }

    @GetMapping("/type/{terrainId}/details")
    public List<Object[]> getTypeByTerrainId(@PathVariable Long terrainId){
        return terrainService.getTypeByTerrainId(terrainId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Terrain> update(@PathVariable Long id, @RequestBody Terrain terrain){
        return terrainService.updateTerrain(id, terrain);
    }
}
