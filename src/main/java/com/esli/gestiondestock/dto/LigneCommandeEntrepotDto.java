package com.esli.gestiondestock.dto;

import java.math.BigDecimal;

import com.esli.gestiondestock.model.Commandeentrepot;
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
}
