package com.esli.gestiondestock.dto;

import java.math.BigDecimal;

import com.esli.gestiondestock.model.Commandeentrepot;
import com.esli.gestiondestock.model.LigneCommandeEntrepot;
import com.esli.gestiondestock.model.Produit;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LigneCommandeEntrepotDto {
    private Integer id;

    private ProduitDto produit;

    private CommandeentrepotDto commandeentrepot;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    public static LigneCommandeEntrepotDto fromEntity(LigneCommandeEntrepot ligneCommandeEntrepot) {
        if (ligneCommandeEntrepot == null) {
            return null;
        }
        return LigneCommandeEntrepotDto.builder()
                .id(ligneCommandeEntrepot.getId())
                .produit(ProduitDto.fromEntity(ligneCommandeEntrepot.getProduit()))
                .quantite(ligneCommandeEntrepot.getQuantite())
                .prixUnitaire(ligneCommandeEntrepot.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeEntrepot toEntity(LigneCommandeEntrepotDto dto) {
        if (dto == null) {
            return null;
        }

        LigneCommandeEntrepot ligneCommandeEntrepot = new LigneCommandeEntrepot();
        ligneCommandeEntrepot.setId(dto.getId());
        ligneCommandeEntrepot.setProduit(ProduitDto.toEntity(dto.getProduit()));
        ligneCommandeEntrepot.setPrixUnitaire(dto.getPrixUnitaire());
        ligneCommandeEntrepot.setQuantite(dto.getQuantite());
        return ligneCommandeEntrepot;
    }
}
