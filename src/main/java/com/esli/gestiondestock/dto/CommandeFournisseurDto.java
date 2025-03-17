package com.esli.gestiondestock.dto;

import com.esli.gestiondestock.model.Entrepot;
import com.esli.gestiondestock.model.EtatCommande;
import com.esli.gestiondestock.model.Fournisseur;
import jakarta.persistence.*;

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
}
