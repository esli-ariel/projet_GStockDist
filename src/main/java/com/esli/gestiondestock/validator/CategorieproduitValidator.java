package com.esli.gestiondestock.validator;


import java.util.ArrayList;
import java.util.List;

import com.esli.gestiondestock.dto.CategorieproduitDto;
import org.springframework.util.StringUtils;

public class CategorieproduitValidator {

  public static List<String> validate(CategorieproduitDto categorieproduitDto) {
    List<String> errors = new ArrayList<>();

    if (categorieproduitDto == null || !StringUtils.hasLength(categorieproduitDto.getCode())) {
      errors.add("Veuillez renseigner le code de la categorie");
    }
    return errors;
  }

}
