package com.esli.gestiondestock.model;

import ch.qos.logback.core.net.server.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commandeentrepot")
public class Commandeentrepot extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @Column(name = "etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    @ManyToOne
    @JoinColumn(name = "idmagasin")
    private Magasin magasin;

    @ManyToOne
    @JoinColumn(name = "entrepot_id") // Vérifiez l'existence de cette colonne en BDD
    private Entrepot entrepot;

    @OneToMany(mappedBy = "commandeentrepot")
    private List<LigneCommandeEntrepot> ligneCommandeEntrepots;
}
