package com.esli.gestiondestock.dto;

import java.math.BigDecimal;
import java.util.List;

import com.esli.gestiondestock.model.Categorieproduit;
import com.esli.gestiondestock.model.LigneCommandeEntrepot;
import com.esli.gestiondestock.model.LigneCommandeFournisseur;
import com.esli.gestiondestock.model.MvtStocks;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProduitDto {
    private Integer id;

    private String codeProduit;

    private String designation;

    private BigDecimal prixUnitaireHt;


    private BigDecimal tauxTva;


    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategorieproduitDto categorieproduit;

    private List<LigneCommandeEntrepotDto> ligneCommandeEntrepots;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    private List<MvtStocksDto> mvtStocks;
}
