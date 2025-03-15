package com.esli.gestiondestock.model;

import ch.qos.logback.core.net.server.Client;
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
@Table(name = "Commandeentrepot")
public class Commandeentrepot extends AbstractEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "datecommande")
    private Instant dateCommande;

    @Column(name = "etatcommande")
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    @ManyToOne
    @JoinColumn(name = "idMagasin")
    private Magasin magasins;

    @OneToMany(mappedBy = "commandeEntrepot")
    private List<LigneCommandeEntrepot> ligneCommandeEntrepots;

}
