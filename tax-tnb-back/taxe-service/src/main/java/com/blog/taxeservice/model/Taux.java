package com.blog.taxeservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Taux {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private double montant;
    @ManyToOne
    private Categorie categorie;
}