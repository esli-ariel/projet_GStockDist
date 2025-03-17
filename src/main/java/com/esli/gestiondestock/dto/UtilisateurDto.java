package com.esli.gestiondestock.dto;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.esli.gestiondestock.model.Roles;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateDeNaissance;

    private String moteDePasse;

    private String photo;

    private List<RolesDto> roles;
}
