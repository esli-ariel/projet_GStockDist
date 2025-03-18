package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.EntrepotDto;
import java.util.List;

public interface EntrepotService {

 EntrepotDto save(EntrepotDto dto);

 EntrepotDto findById(Integer id);

  List<EntrepotDto> findAll();

  void delete(Integer id);

}
