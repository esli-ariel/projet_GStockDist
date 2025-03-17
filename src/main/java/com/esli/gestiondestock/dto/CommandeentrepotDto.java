package com.esli.gestiondestock.dto;

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
}
