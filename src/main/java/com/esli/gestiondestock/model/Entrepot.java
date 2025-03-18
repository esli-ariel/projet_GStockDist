package com.esli.gestiondestock.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Entrepot")
public class Entrepot extends AbstractEntity{

    @Column(name = "nom")
    private String nom;

    @Column(name = "photo")
    private String photo;


    @Column(name = "numTel")
    private String numTel;

    @OneToMany(mappedBy = "entrepot")
    private List<CommandeFournisseur> commandeFournisseurs;

    @OneToMany(mappedBy = "entrepot")
    private List<Commandeentrepot> commandeentrepots;
}
