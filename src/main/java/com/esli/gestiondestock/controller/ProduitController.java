package com.esli.gestiondestock.controller;

import com.esli.gestiondestock.controller.api.ProduitApi;
import com.esli.gestiondestock.dto.ProduitDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeFournisseurDto;
//import com.esli.gestiondestock.dto.LigneVenteDto;
import com.esli.gestiondestock.services.ProduitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduitController implements ProduitApi {

  private ProduitService produitService;

  @Autowired
  public ProduitController(
      ProduitService produitService
  ) {
    this.produitService = produitService;
  }

  @Override
  public ProduitDto save(ProduitDto dto) {
    return produitService.save(dto);
  }

  @Override
  public ProduitDto findById(Integer id) {
    return produitService.findById(id);
  }

  @Override
  public ProduitDto findByCodeProduit(String codeProduit) {
    return produitService.findByCodeProduit(codeProduit);
  }

  @Override
  public List<ProduitDto> findAll() {
    return produitService.findAll();
  }

  @Override
  public List<LigneCommandeEntrepotDto> findHistoriaueCommandeClient(Integer idProduit) {
    return List.of();
  }

//  @Override
//  public List<LigneVenteDto> findHistoriqueVentes(Integer idProduit) {
//    return produitService.findHistoriqueVentes(idProduit);
//  }

  @Override
  public List<LigneCommandeEntrepotDto> findHistoriaueCommandeEntrepot(Integer idProduit) {
    return produitService.findHistoriaueCommandeEntrepot(idProduit);
  }

  @Override
  public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idProduit) {
    return produitService.findHistoriqueCommandeFournisseur(idProduit);
  }



  @Override
  public List<ProduitDto> findAllProduitByIdCategorieproduit(Integer idCategorieproduit) {
    return produitService.findAllProduitByIdCategorieproduit(idCategorieproduit);
  }

  @Override
  public void delete(Integer id) {
    produitService.delete(id);
  }
}
