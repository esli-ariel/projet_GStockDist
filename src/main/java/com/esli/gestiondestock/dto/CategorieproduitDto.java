package com.esli.gestiondestock.dto;

import com.esli.gestiondestock.model.Categorieproduit;
import com.esli.gestiondestock.model.Produit;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorieproduitDto {
    private Integer id;

    private String code;

    private String designation;

    @JsonIgnore
    private List<ProduitDto> produit;

    public static CategorieproduitDto fromEntity(Categorieproduit categorieproduit) {
        if ( Categorieproduit == null) {
            return null;
            // TODO throw an exception
        }

        return CategorieproduitDto.builder()
                .id(Categorieproduit.getId())
                .code(Categorieproduit.getCode())
                .designation(Categorieproduit.getDesignation())
                .build();
    }

    public static Categorieproduit toEntity(CategorieproduitDto categorieproduitDto) {
        if (CategorieproduitDto == null) {
            return null;
            // TODO throw an exception
        }

        Categorieproduit category = new Categorieproduit();
        Categorieproduit.setId(CategorieproduitDto.getId());
        Categorieproduit.setCode(CategorieproduitDto.getCode());
        Categorieproduit.setDesignation(CategorieproduitDto.getDesignation());

        return Categorieproduit;
    }
}
