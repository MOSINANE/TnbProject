package com.blog.taxeservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Terrain {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private int mc;

    @ManyToOne
    private Proprietaire proprietaire;

    @ManyToOne
    private Categorie categorie;

}
