package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.CommandeFournisseurDto;
import com.esli.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.esli.gestiondestock.model.EtatCommande;
import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

  CommandeFournisseurDto save(CommandeFournisseurDto dto);

  CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

  CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

  CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

  CommandeFournisseurDto updateProduit(Integer idCommande, Integer idLigneCommande, Integer idProduit);

  // Delete Produit ==> delete LigneCommandeFournisseur
  CommandeFournisseurDto deleteProduit(Integer idCommande, Integer idLigneCommande);

  CommandeFournisseurDto findById(Integer id);

  CommandeFournisseurDto findByCode(String code);

  List<CommandeFournisseurDto> findAll();

  List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(Integer idCommande);

  void delete(Integer id);

}
