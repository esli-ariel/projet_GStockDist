package com.esli.gestiondestock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.esli.gestiondestock.model.Produit;
import com.esli.gestiondestock.model.TypeMvtStock;
import com.esli.gestiondestock.model.srcMvtStock;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MvtStocksDto {

    private Integer id;

    private Instant dateMvt;

    private BigDecimal quantite;

    private ProduitDto produit;

    private TypeMvtStock typeMvt;

    private srcMvtStock sourceMvt;
}
