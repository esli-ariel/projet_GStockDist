package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.ProduitDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeFournisseurDto;
//import com.esli.gestiondestock.dto.LigneVenteDto;
import java.util.List;

public interface ProduitService {

  ProduitDto save(ProduitDto dto);

  ProduitDto findById(Integer id);

  ProduitDto findByCodeProduit(String codeProduit);

  List<ProduitDto> findAll();

  //List<LigneVenteDto> findHistoriqueVentes(Integer idProduit);

  List<LigneCommandeEntrepotDto> findHistoriaueCommandeEntrepot(Integer idProduit);

  List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idProduit);

  List<ProduitDto> findAllProduitByIdCategorieproduit(Integer idCategorieproduit);

  void delete(Integer id);

}
