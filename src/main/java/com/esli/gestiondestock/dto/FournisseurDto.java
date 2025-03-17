package com.esli.gestiondestock.dto;

import java.math.BigDecimal;
import java.util.List;

import com.esli.gestiondestock.model.CommandeFournisseur;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FournisseurDto {
    private Integer id;

    private String nom;

    private String prenom;

    private String photo;

    private String mail;

    private String numTel;

    private List<CommandeFournisseurDto> commandeFournisseurs;
}
