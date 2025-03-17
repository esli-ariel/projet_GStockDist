package com.esli.gestiondestock.dto;

import java.math.BigDecimal;
import java.util.List;

import com.esli.gestiondestock.model.*;
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

    public static ProduitDto fromEntity(Produit produit) {
        if (produit == null) {
            return null;
        }
        return ProduitDto.builder()
                .id(produit.getId())
                .codeProduit(produit.getCodeProduit())
                .designation(produit.getDesignation())
                .photo(produit.getPhoto())
                .prixUnitaireHt(produit.getPrixUnitaireHt())
                .prixUnitaireTtc(produit.getPrixUnitaireTtc())
                .tauxTva(produit.getTauxTva())
                .categorieproduit(CategorieproduitDto.fromEntity(produit.getCategorieproduit()))
                .build();
    }

    public static Produit toEntity(ProduitDto produitDto) {
        if (produitDto == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setCodeProduit(produitDto.getCodeProduit());
        produit.setDesignation(produitDto.getDesignation());
        produit.setPhoto(produitDto.getPhoto());
        produit.setPrixUnitaireHt(produitDto.getPrixUnitaireHt());
        produit.setPrixUnitaireTtc(produitDto.getPrixUnitaireTtc());
        produit.setTauxTva(produitDto.getTauxTva());
        produit.setCategorieproduit(CategorieproduitDto.toEntity(produitDto.getCategorieproduit()));
        return produit;
    }


}
