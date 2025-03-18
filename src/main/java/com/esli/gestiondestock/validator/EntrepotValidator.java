package com.esli.gestiondestock.validator;


import java.util.ArrayList;
import java.util.List;

import com.esli.gestiondestock.dto.EntrepotDto;
import com.esli.gestiondestock.model.Entrepot;
import org.springframework.util.StringUtils;

public class EntrepotValidator {

  public static List<String> validate(EntrepotDto dto) {
    List<String> errors = new ArrayList<>();
    if (dto == null) {
      errors.add("Veuillez renseigner le nom de l'entreprise");
      errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
      return errors;
    }

    if (!StringUtils.hasLength(dto.getNom())) {
      errors.add("Veuillez renseigner le nom de l'entreprise");
    }
    if (!StringUtils.hasLength(dto.getNumTel())) {
      errors.add("Veuillez reseigner le numero de telephone de l'entreprise");
    }

    return errors;
  }

}
