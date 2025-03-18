package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.MagasinDto;
import java.util.List;

public interface MagasinService {

  MagasinDto save(MagasinDto dto);

  MagasinDto findById(Integer id);

  List<MagasinDto> findAll();

  void delete(Integer id);

}
