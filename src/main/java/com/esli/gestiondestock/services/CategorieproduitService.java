package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.CategorieproduitDto;
import java.util.List;

public interface CategorieproduitService {

  CategorieproduitDto save(CategorieproduitDto dto);

  CategorieproduitDto findById(Integer id);

  CategorieproduitDto findByCode(String code);

  List<CategorieproduitDto> findAll();

  void delete(Integer id);

}
