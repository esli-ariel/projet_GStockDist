package com.esli.gestiondestock.controller;

import com.esli.gestiondestock.controller.api.CategorieproduitApi;
import com.esli.gestiondestock.dto.CategorieproduitDto;
import com.esli.gestiondestock.services.CategorieproduitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieproduitController implements CategorieproduitApi {

  private CategorieproduitService categorieproduitService;

  @Autowired
  public CategorieproduitController(CategorieproduitService categorieproduitService) {
    this.categorieproduitService = categorieproduitService;
  }

  @Override
  public CategorieproduitDto save(CategorieproduitDto dto) {
    return categorieproduitService.save(dto);
  }

  @Override
  public CategorieproduitDto findById(Integer idCategorieproduit) {
    return categorieproduitService.findById(idCategorieproduit);
  }

  @Override
  public CategorieproduitDto findByCode(String codeCategorieproduit) {
    return categorieproduitService.findByCode(codeCategorieproduit);
  }

  @Override
  public List<CategorieproduitDto> findAll() {
    return categorieproduitService.findAll();
  }

  @Override
  public void delete(Integer id) {
    categorieproduitService.delete(id);
  }
}
