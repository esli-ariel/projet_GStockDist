//package com.esli.gestiondestock.model;
//
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//
//import java.math.BigDecimal;
//
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@Entity
//@Table(name = "lignevente")
//public class LigneVente extends AbstractEntity{
//    @ManyToOne
//    @JoinColumn(name = "idvente")
//    private Ventes vente;
//
//    @ManyToOne
//    @JoinColumn(name = "idarticle")
//    private Produit article;
//
//    @Column(name = "quantite")
//    private BigDecimal quantite;
//
//    @Column(name = "prixunitaire")
//    private BigDecimal prixUnitaire;
//
//
//}
