package com.blog.taxeservice.repository;

import com.blog.taxeservice.model.Terrain;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerrainRepository extends JpaRepository<Terrain,Long> {
    @Query("select p.nom from Terrain t inner join Proprietaire p on t.proprietaire.id=p.id where t.id= :terrainId")
    List<Object[]> getNomByTerrainId(@Param("terrainId") Long terrainId);

    @Query("select c.type from Terrain t inner join Categorie c on t.categorie.id=c.id where t.id= :terrainId")
    List<Object[]> getTypeByTerrainId(@Param("terrainId") Long terrainId);
}
