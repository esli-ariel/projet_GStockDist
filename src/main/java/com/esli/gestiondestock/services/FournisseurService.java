package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.FournisseurDto;
import java.util.List;

public interface FournisseurService {

  FournisseurDto save(FournisseurDto dto);

  FournisseurDto findById(Integer id);

  List<FournisseurDto> findAll();

  void delete(Integer id);

}
