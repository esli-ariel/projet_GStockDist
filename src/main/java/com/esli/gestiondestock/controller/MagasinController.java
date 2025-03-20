package com.esli.gestiondestock.controller;

import com.esli.gestiondestock.controller.api.MagasinApi;
import com.esli.gestiondestock.dto.MagasinDto;
import com.esli.gestiondestock.services.MagasinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MagasinController implements MagasinApi {

  private MagasinService magasinService;

  @Autowired
  public MagasinController(MagasinService magasinService) {
    this.magasinService = magasinService;
  }

  @Override
  public MagasinDto save(MagasinDto dto) {
    return magasinService.save(dto);
  }

  @Override
  public MagasinDto findById(Integer id) {
    return magasinService.findById(id);
  }

  @Override
  public List<MagasinDto> findAll() {
    return magasinService.findAll();
  }

  @Override
  public void delete(Integer id) {
    magasinService.delete(id);
  }
}
