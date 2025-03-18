package com.esli.gestiondestock.validator;

import java.util.ArrayList;
import java.util.List;

import com.esli.gestiondestock.dto.ProduitDto;
import org.springframework.util.StringUtils;

public class ProduitValidator {

  public static List<String> validate(ProduitDto dto) {
    List<String> errors = new ArrayList<>();

    if (dto == null) {
      errors.add("Veuillez renseigner le code du produit");
      errors.add("Veuillez renseigner la designation du produit");
      errors.add("Veuillez renseigner le prix unitaire HT du produit");
      errors.add("Veuillez renseigner le taux TVA du produit");
      errors.add("Veuillez renseigner le prix unitaire TTC du produit");
      errors.add("Veuillez selectionner une categorie");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getCodeProduit())) {
      errors.add("Veuillez renseigner le code du produit");
    }
    if (!StringUtils.hasLength(dto.getDesignation())) {
      errors.add("Veuillez renseigner la designation du produit");
    }
    if (dto.getPrixUnitaireHt() == null) {
      errors.add("Veuillez renseigner le prix unitaire du produit");
    }
    if (dto.getTauxTva() == null) {
      errors.add("Veuillez renseigner le taux TVA du produit");
    }
    if (dto.getPrixUnitaireTtc() == null) {
      errors.add("Veuillez renseigner le prix unitaire TTC du produit");
    }
    if (dto.getCategorieproduit() == null || dto.getCategorieproduit().getId() == null) {
      errors.add("Veuillez selectionner une categorie");
    }
    return errors;
  }

}
