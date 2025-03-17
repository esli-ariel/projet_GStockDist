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
        if ( categorieproduit == null) {
            return null;
            // TODO throw an exception
        }

        return CategorieproduitDto.builder()
                .id(categorieproduit.getId())
                .code(categorieproduit.getCode())
                .designation(categorieproduit.getDesignation())
                .build();
    }


    public static Categorieproduit toEntity(CategorieproduitDto categorieproduitDto) {
        if (categorieproduitDto == null) {
            return null;
            // TODO throw an exception
        }

        Categorieproduit categorieproduit = new Categorieproduit();
        categorieproduit.setId(categorieproduitDto.getId());
        categorieproduit.setCode(categorieproduitDto.getCode());
        categorieproduit.setDesignation(categorieproduitDto.getDesignation());

        return categorieproduit;
    }
}
