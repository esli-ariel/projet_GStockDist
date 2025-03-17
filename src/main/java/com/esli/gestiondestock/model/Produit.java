package com.esli.gestiondestock.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "produit")
public class Produit extends AbstractEntity {
    @Column(name = "codeproduit")
    private String codeProduit;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prixunitaireht")
    private BigDecimal prixUnitaireHt;

    @Column(name = "tauxtva")
    private BigDecimal tauxTva;

    @Column(name = "prixunitairettc")
    private BigDecimal prixUnitaireTtc;

    @Column(name = "photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name= "idcategorie")
    private Categorieproduit categorieproduit;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommandeEntrepot> ligneCommandeEntrepots;

    @OneToMany(mappedBy = "produit")
    private List<LigneCommandeFournisseur> ligneCommandeFournisseurs;

    @OneToMany(mappedBy = "produit")
    private List<MvtStocks> mvtStocks;
}
