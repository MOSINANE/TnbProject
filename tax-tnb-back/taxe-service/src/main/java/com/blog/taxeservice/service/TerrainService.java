package com.blog.taxeservice.service;

import com.blog.taxeservice.model.Terrain;
import com.blog.taxeservice.repository.TerrainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TerrainService {
    private final TerrainRepository terrainRepository;

    public <S extends Terrain> S save(S entity) {
        return terrainRepository.save(entity);
    }

    public List<Terrain> findAll() {
        return terrainRepository.findAll();
    }

    public Optional<Terrain> findById(Long id) {
        return terrainRepository.findById(id);
    }

    public void deleteById(Long id) {
        terrainRepository.deleteById(id);
    }

    public List<Object[]> getNomByTerrainId(Long terrainId) {
        return terrainRepository.getNomByTerrainId(terrainId);
    }

    public List<Object[]> getTypeByTerrainId(Long terrainId){
        return terrainRepository.getTypeByTerrainId(terrainId);
    }

    public ResponseEntity<Terrain> updateTerrain(Long id, Terrain terrain) {
        Optional<Terrain> optionalTerrain = terrainRepository.findById(id);

        if (optionalTerrain.isPresent()) {
            Terrain existingTerrain = optionalTerrain.get();
            existingTerrain.setMc(terrain.getMc());
            existingTerrain.setProprietaire(terrain.getProprietaire());
            existingTerrain.setCategorie(terrain.getCategorie());

            Terrain updatedTerrain = terrainRepository.save(existingTerrain);
            return ResponseEntity.ok(updatedTerrain);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
