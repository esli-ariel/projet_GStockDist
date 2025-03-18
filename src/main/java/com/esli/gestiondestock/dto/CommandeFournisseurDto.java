package com.esli.gestiondestock.dto;

import com.esli.gestiondestock.model.CommandeFournisseur;
import com.esli.gestiondestock.model.Entrepot;
import com.esli.gestiondestock.model.EtatCommande;
import com.esli.gestiondestock.model.Fournisseur;
import jakarta.persistence.*;
import java.util.List;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeFournisseurDto {
    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private EntrepotDto entrepot;

    private FournisseurDto fournisseur;

    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .etatCommande(commandeFournisseur.getEtatCommande())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(dto.getId());
        commandeFournisseur.setCode(dto.getCode());
        commandeFournisseur.setDateCommande(dto.getDateCommande());
        commandeFournisseur.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));
        commandeFournisseur.setEtatCommande(dto.getEtatCommande());
        return commandeFournisseur;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
