package com.esli.gestiondestock.controller;

import com.esli.gestiondestock.controller.api.EntrepotApi;
import com.esli.gestiondestock.dto.EntrepotDto;
import com.esli.gestiondestock.services.EntrepotService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntrepotController implements EntrepotApi {

  private EntrepotService entrepotService;

  @Autowired
  public EntrepotController(EntrepotService entrepotService) {
    this.entrepotService = entrepotService;
  }

  @Override
  public EntrepotDto save(EntrepotDto dto) {
    return entrepotService.save(dto);
  }

  @Override
  public EntrepotDto findById(Integer id) {
    return entrepotService.findById(id);
  }

  @Override
  public List<EntrepotDto> findAll() {
    return entrepotService.findAll();
  }

  @Override
  public void delete(Integer id) {
    entrepotService.delete(id);
  }
}
