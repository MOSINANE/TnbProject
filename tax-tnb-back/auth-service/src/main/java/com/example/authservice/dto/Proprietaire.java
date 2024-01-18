package com.example.authservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proprietaire {
    private int id;

    private String nom;
    private String prenom;
    private String cin;
    private int propid;
}
