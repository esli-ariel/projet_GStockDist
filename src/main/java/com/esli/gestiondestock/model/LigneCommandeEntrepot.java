package com.esli.gestiondestock.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "lignecommandeentrepot")
public class LigneCommandeEntrepot extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "idproduit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "idcommandeentrepot")
    private Commandeentrepot commandeentrepot;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "prixunitaire")
    private BigDecimal prixUnitaire;

}
