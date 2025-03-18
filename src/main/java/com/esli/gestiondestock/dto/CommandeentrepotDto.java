package com.esli.gestiondestock.dto;

import com.esli.gestiondestock.model.Commandeentrepot;
import com.esli.gestiondestock.model.Entrepot;
import com.esli.gestiondestock.model.EtatCommande;
import com.esli.gestiondestock.model.Magasin;
import jakarta.persistence.*;

import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeentrepotDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private MagasinDto magasin;

    private EntrepotDto entrepot;

    public static CommandeentrepotDto fromEntity(Commandeentrepot commandeClient) {
        if (commandeClient == null) {
            return null;
        }
        return CommandeentrepotDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .etatCommande(commandeClient.getEtatCommande())
                .magasin(MagasinDto.fromEntity(Commandeentrepot.getMagasin()))
                .build();

    }

    public static Commandeentrepot toEntity(CommandeentrepotDto dto) {
        if (dto == null) {
            return null;
        }
        Commandeentrepot commandeentrepot = new Commandeentrepot();
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
