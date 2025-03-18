package com.esli.gestiondestock.validator;


import java.util.ArrayList;
import java.util.List;

import com.esli.gestiondestock.dto.MagasinDto;
import org.springframework.util.StringUtils;

public class MagasinValidator {

  public static List<String> validate(MagasinDto dto) {
    List<String> errors = new ArrayList<>();

    if (dto == null) {
      errors.add("Veuillez renseigner le nom du magasin");
      errors.add("Veuillez renseigner le Mail du magasin");
      errors.add("Veuillez renseigner le numero de telephone du magasin");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getNom())) {
      errors.add("Veuillez renseigner le nom du magasin");
    }
    if (!StringUtils.hasLength(dto.getMail())) {
      errors.add("Veuillez renseigner le Mail du magasin");
    }
    if (!StringUtils.hasLength(dto.getNumTel())) {
      errors.add("Veuillez renseigner le numero de telephone du magasin");
    }
    return errors;
  }

}
