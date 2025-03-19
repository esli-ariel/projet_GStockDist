package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.CommandeEntrepotDto;
import com.esli.gestiondestock.dto.CommandeFournisseurDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.esli.gestiondestock.model.EtatCommande;
import java.math.BigDecimal;
import java.util.List;

public interface CommandeEntrepotService {

  CommandeEntrepotDto save(CommandeEntrepotDto dto);

  CommandeEntrepotDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

  CommandeEntrepotDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

  CommandeEntrepotDto updateentrepot(Integer idCommande, Integer idEntrepot);

  CommandeEntrepotDto updateEntrepot(Integer idCommande, Integer idEntrepot);

  CommandeEntrepotDto updateProduit(Integer idCommande, Integer idLigneCommande, Integer newIdProduit);

  // Delete Produit ==> delete LigneCommandeClient
  CommandeEntrepotDto deleteProduit(Integer idCommande, Integer idLigneCommande);

  CommandeEntrepotDto findById(Integer id);

  CommandeEntrepotDto findByCode(String code);

  List<CommandeEntrepotDto> findAll();

  List<LigneCommandeEntrepotDto> findAllLignesCommandesEntrepotByCommandeEntrepotId(Integer idCommande);

  void delete(Integer id);

  CommandeFournisseurDto passerCommandeFournisseur(Integer idEntrepot, Integer idFournisseur, List<LigneCommandeFournisseurDto> lignesCommande);
}
