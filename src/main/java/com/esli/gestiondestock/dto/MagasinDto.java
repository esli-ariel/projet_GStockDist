package com.esli.gestiondestock.dto;

import java.math.BigDecimal;
import java.util.List;

import com.esli.gestiondestock.model.Commandeentrepot;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MagasinDto {
    private Integer id;

    private String nom;

    private String mail;

    private String numTel;

    private List<CommandeentrepotDto> CommandeEntrepots;
}
