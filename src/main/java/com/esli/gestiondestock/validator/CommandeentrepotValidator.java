package com.esli.gestiondestock.validator;


import java.util.ArrayList;
import java.util.List;

import com.esli.gestiondestock.dto.CommandeentrepotDto;
import org.springframework.util.StringUtils;

public class CommandeentrepotValidator {


  public static List<String> validate(CommandeentrepotDto dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Veuillez renseigner le code de la commande");
      errors.add("Veuillez renseigner la date de la commande");
      errors.add("Veuillez renseigner l'etat de la commande");
      errors.add("Veuillez renseigner le magasin");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getCode())) {
      errors.add("Veuillez renseigner le code de la commande");
    }
    if (dto.getDateCommande() == null) {
      errors.add("Veuillez renseigner la date de la commande");
    }
    if (!StringUtils.hasLength(dto.getEtatCommande().toString())) {
      errors.add("Veuillez renseigner l'etat de la commande");
    }
    if (dto.getMagasin() == null || dto.getMagasin().getId() == null) {
      errors.add("Veuillez renseigner le magasin");
    }

    return errors;
  }

}
