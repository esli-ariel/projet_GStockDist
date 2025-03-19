package com.esli.gestiondestock.dto;

import com.esli.gestiondestock.model.Entrepot;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntrepotDto {
    private Integer id;

    private String nom;

    private String photo;

    private String numTel;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    @JsonIgnore
    private List<CommandeEntrepotDto> commandeentrepots;

    public static EntrepotDto fromEntity(Entrepot entrepot) {
        if (entrepot == null) {
            return null;
        }
        return EntrepotDto.builder()
                .id(entrepot.getId())
                .nom(entrepot.getNom())
                .photo(entrepot.getPhoto())
                .numTel(entrepot.getNumTel())
                .build();
    }

    public static Entrepot toEntity(EntrepotDto dto) {
        if (dto == null) {
            return null;
        }
        Entrepot entrepot = new Entrepot();
        entrepot.setId(dto.getId());
        entrepot.setNom(dto.getNom());
        entrepot.setPhoto(dto.getPhoto());
        entrepot.setNumTel(dto.getNumTel());

        return entrepot;
    }

}
