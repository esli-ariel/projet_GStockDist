//package com.esli.gestiondestock.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import java.time.Instant;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Table(name = "ventes")
//public class Ventes extends AbstractEntity {
//    @Column(name = "code")
//    private String code;
//
//    @Column(name = "datevente")
//    private Instant dateVente;
//
//    @Column(name = "commentaire")
//    private String commentaire;
//
//    @Column(name = "identreprise")
//    private Integer idEntreprise;
//
//    @OneToMany(mappedBy = "vente")
//    private List<LigneVente> ligneVentes;
//
//}
