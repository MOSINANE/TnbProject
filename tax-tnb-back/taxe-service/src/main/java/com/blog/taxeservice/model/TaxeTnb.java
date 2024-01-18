package com.blog.taxeservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaxeTnb {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String label;
    private String description;
    private int tnbyear;
    private Double montantbase;
    @ManyToOne
    private Terrain terrain;
    @ManyToOne
    private Proprietaire proprietaire;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private Taux taux;

}
