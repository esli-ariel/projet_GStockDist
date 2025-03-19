package com.esli.gestiondestock.dto;

import java.util.List;

import com.esli.gestiondestock.model.Magasin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MagasinDto {
    private Integer id;

    private String nom;

    private String mail;

    private String numTel;

    @JsonIgnore
    private List<CommandeEntrepotDto> CommandeEntrepots;

    public static MagasinDto fromEntity(Magasin magasin) {
        if (magasin == null) {
            return null;
        }
        return MagasinDto.builder()
                .id(magasin.getId())
                .nom(magasin.getNom())
                .mail(magasin.getMail())
                .numTel(magasin.getNumTel())
                .build();
    }

    public static Magasin toEntity(MagasinDto dto) {
        if (dto == null) {
            return null;
        }
        Magasin magasin = new Magasin();
        magasin.setId(dto.getId());
        magasin.setNom(dto.getNom());
        magasin.setMail(dto.getMail());
        magasin.setNumTel(dto.getNumTel());
        return magasin;
    }

}
