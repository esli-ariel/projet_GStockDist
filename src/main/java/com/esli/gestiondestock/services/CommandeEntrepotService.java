package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.CommandeentrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.model.EtatCommande;
import java.math.BigDecimal;
import java.util.List;

public interface CommandeEntrepotService {

  CommandeentrepotDto save(CommandeentrepotDto dto);

  CommandeentrepotDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

  CommandeentrepotDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

  CommandeentrepotDto updateentrepot(Integer idCommande, Integer idEntrepot);

  CommandeentrepotDto updateProduit(Integer idCommande, Integer idLigneCommande, Integer newIdProduit);

  // Delete Produit ==> delete LigneCommandeClient
  CommandeentrepotDto deleteProduit(Integer idCommande, Integer idLigneCommande);

  CommandeentrepotDto findById(Integer id);

  CommandeentrepotDto findByCode(String code);

  List<CommandeentrepotDto> findAll();

  List<LigneCommandeEntrepotDto> findAllLignesCommandesEntrepotByCommandeEntrepotId(Integer idCommande);

  void delete(Integer id);

}
