package com.esli.gestiondestock.dto;

import com.esli.gestiondestock.model.CommandeFournisseur;
import com.esli.gestiondestock.model.Commandeentrepot;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntrepotDto {
    private Integer id;

    private String nom;

    private String photo;

    private String mail;

    private String numTel;


    private List<CommandeFournisseurDto> commandeFournisseurs;


    private List<CommandeentrepotDto> commandeentrepots;
}
