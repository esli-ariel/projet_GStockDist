package com.esli.gestiondestock.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.esli.gestiondestock.model.MvtStocks;
import com.esli.gestiondestock.model.TypeMvtStock;
import com.esli.gestiondestock.model.SrcMvtStock;
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

    private SrcMvtStock sourceMvt;

    public static MvtStocksDto fromEntity( MvtStocks mvtStocks) {
        if (mvtStocks == null) {
            return null;
        }

        return MvtStocksDto.builder()
                .id(mvtStocks.getId())
                .dateMvt(mvtStocks.getDateMvt())
                .quantite(mvtStocks.getQuantite())
                .produit(ProduitDto.fromEntity(mvtStocks.getProduit()))
                .typeMvt(mvtStocks.getTypeMvt())
                .sourceMvt(mvtStocks.getSourceMvt())
                .build();
    }

    public static MvtStocks toEntity(MvtStocksDto dto) {
        if (dto == null) {
            return null;
        }

        MvtStocks mvtStk = new  MvtStocks();
        mvtStk.setId(dto.getId());
        mvtStk.setDateMvt(dto.getDateMvt());
        mvtStk.setQuantite(dto.getQuantite());
        mvtStk.setProduit(ProduitDto.toEntity(dto.getProduit()));
        mvtStk.setTypeMvt(dto.getTypeMvt());
        mvtStk.setSourceMvt(dto.getSourceMvt());
        return mvtStk;
    }
}
