package com.esli.gestiondestock.dto;

import com.esli.gestiondestock.model.CommandeEntrepot;
import com.esli.gestiondestock.model.EtatCommande;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeEntrepotDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private MagasinDto magasin;

    private EntrepotDto entrepot;

    public static CommandeEntrepotDto fromEntity(CommandeEntrepot commandeentrepot) {
        if (commandeentrepot == null) {
            return null;
        }
        return CommandeEntrepotDto.builder()
                .id(commandeentrepot.getId())
                .code(commandeentrepot.getCode())
                .dateCommande(commandeentrepot.getDateCommande())
                .etatCommande(commandeentrepot.getEtatCommande())
                .magasin(MagasinDto.fromEntity(commandeentrepot.getMagasin()))
                .build();

    }

    public static CommandeEntrepot toEntity(CommandeEntrepotDto dto) {
        if (dto == null) {
            return null;
        }
        CommandeEntrepot commandeentrepot = new CommandeEntrepot();
        commandeentrepot.setId(dto.getId());
        commandeentrepot.setCode(dto.getCode());
        commandeentrepot.setMagasin(MagasinDto.toEntity(dto.getMagasin()));
        commandeentrepot.setDateCommande(dto.getDateCommande());
        commandeentrepot.setEtatCommande(dto.getEtatCommande());
        return commandeentrepot;
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.etatCommande);
    }
}
